package K23cnt1.nnhLab08.service;

import K23cnt1.nnhLab08.entity.nnhBook;
import K23cnt1.nnhLab08.repository.nnhBookRepository;
import
        org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class nnhBookService {
    @Autowired
    private nnhBookRepository bookRepository;
    public List<nnhBook> getAllBooks() {
        return bookRepository.findAll();
    }
    public nnhBook saveBook(nnhBook book) {
        return bookRepository.save(book);
    }
    public nnhBook getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
