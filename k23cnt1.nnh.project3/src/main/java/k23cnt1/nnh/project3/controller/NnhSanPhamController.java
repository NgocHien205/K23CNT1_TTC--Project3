package k23cnt1.nnh.project3.controller;

import k23cnt1.nnh.project3.entity.NnhSanPham;
import k23cnt1.nnh.project3.repository.NnhDanhMucRepository;
import k23cnt1.nnh.project3.repository.NnhSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NnhSanPhamController {

    @Autowired
    private NnhSanPhamRepository sanPhamRepo;

    @Autowired
    private NnhDanhMucRepository danhMucRepo;

    // 1. Trang chủ: Hiển thị danh sách sản phẩm
    // Map cả 2 đường dẫn: "/" và "/NnhIndex" đều vào đây
    @GetMapping({"/", "/NnhIndex"})
    public String viewHomePage(Model model) {
        model.addAttribute("listSanPhams", sanPhamRepo.findAll());
        return "NnhIndex";
    }

    // 2. Form thêm mới
    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model) {
        NnhSanPham sanPham = new NnhSanPham();
        model.addAttribute("sanPham", sanPham);
        model.addAttribute("listDanhMucs", danhMucRepo.findAll());
        return "NnhProduct";
    }

    // 3. Lưu sản phẩm
    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("sanPham") NnhSanPham sanPham) {
        sanPhamRepo.save(sanPham);
        return "redirect:/NnhIndex"; // Quay lại trang danh sách
    }

    // 4. Xóa sản phẩm
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") Integer id) {
        sanPhamRepo.deleteById(id);
        return "redirect:/NnhIndex"; // Quay lại trang danh sách
    }
}