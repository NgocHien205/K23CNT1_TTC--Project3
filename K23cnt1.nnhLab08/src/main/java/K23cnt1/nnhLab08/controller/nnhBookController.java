package K23cnt1.nnhLab08.controller;

import K23cnt1.nnhLab08.entity.nnhAuthor;
import K23cnt1.nnhLab08.entity.nnhBook;
import K23cnt1.nnhLab08.service.nnhAuthorService;
import K23cnt1.nnhLab08.service.nnhBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private nnhBookService bookService;
    @Autowired
    private nnhAuthorService authorService;
    private static final String UPLOAD_DIR =
            "src/main/resources/static/";
    private static final String
            UPLOAD_PathFile="images/products/";
    // Hiển thị toàn bộ sách
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/book-list";
    }
    // Thêm mới sách
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new nnhBook());
        model.addAttribute("authors",
                authorService.getAllAuthors());
        return "books/book-form";
    }
    @PostMapping("/new")
    public String saveBook(@ModelAttribute nnhBook book,
                           @RequestParam List<Long> authorIds,
                           @RequestParam("imageBook") MultipartFile imageFile) {

        // Xử lý upload ảnh...
        if(!imageFile.isEmpty()) {
            try {
                Path uploadPath = Paths.get(UPLOAD_DIR + UPLOAD_PathFile);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String originalFilename = StringUtils.cleanPath(imageFile.getOriginalFilename());
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFileName = book.getCode() + fileExtension;
                Path filePath = uploadPath.resolve(newFileName);

                Files.copy(imageFile.getInputStream(), filePath);
                book.setImgUrl("/" + UPLOAD_PathFile + newFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // SỬA PHẦN NÀY - Khởi tạo list mới nếu null
        if (book.getAuthors() ) {
            book.setAuthors(new ArrayList<>());
        }

        // Lấy authors từ database
        List<nnhAuthor> authors = authorService.findAllById(authorIds);
        book.setAuthors(authors);

        bookService.saveBook(book);
        return "redirect:/books";
    }
    // Form sửa thông tin sách
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model
            model) {
        nnhBook book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors",
                authorService.getAllAuthors());
        return "books/book-form";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
