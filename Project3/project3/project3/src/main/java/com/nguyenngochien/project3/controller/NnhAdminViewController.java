package com.nguyenngochien.project3.controller;

import com.nguyenngochien.project3.model.NnhProduct;
import com.nguyenngochien.project3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nguyenngochien.project3.repository.NnhRoleRepository;
import com.nguyenngochien.project3.repository.*; // Import repo để count
import java.util.stream.Collectors;
import com.nguyenngochien.project3.model.NnhOrder;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/admin")
public class NnhAdminViewController {

    @Autowired
    private NnhProductService nnhProductService;
    @Autowired
    private NnhCategoryService nnhCategoryService;
    @Autowired
    private NnhUserService nnhUserService;
    @Autowired
    private NnhUserRepository nnhUserRepository;
    @Autowired
    private NnhOrderService nnhOrderService;
    @Autowired private NnhOrderRepository nnhOrderRepository;
    @Autowired private NnhProductRepository nnhProductRepository;
    @Autowired
    private NnhRoleRepository nnhRoleRepository;

    // Dashboard
    @GetMapping("/dashboard")
    public String nnhShowDashboard(Model model) {
        // 1. Thống kê số lượng
        long totalProducts = nnhProductRepository.count();
        long totalUsers = nnhUserRepository.count();
        long totalOrders = nnhOrderRepository.count();

        // 2. Tính tổng doanh thu (Cộng dồn nnhTotalAmount của các đơn hàng)
        double totalRevenue = nnhOrderService.nnhGetAllOrders().stream()
                .filter(o -> o.getNnhTotalAmount() != null) // Tránh null
                .mapToDouble(o -> o.getNnhTotalAmount())
                .sum();

        // Gửi dữ liệu sang View
        model.addAttribute("nnhTotalProducts", totalProducts);
        model.addAttribute("nnhTotalUsers", totalUsers);
        model.addAttribute("nnhTotalOrders", totalOrders);
        model.addAttribute("nnhTotalRevenue", totalRevenue);

        return "admin/nnh-dashboard";
    }

    // 1. Trang Quản lý Danh mục
    @GetMapping("/categories")
    public String nnhShowCategories(Model model) {
        model.addAttribute("nnhCategories", nnhCategoryService.nnhGetAll());
        return "admin/nnh-categories";
    }

    // 2. Trang Quản lý Sản phẩm
    @GetMapping("/products")
    public String nnhShowProducts(Model model) {
        model.addAttribute("nnhProducts", nnhProductService.nnhGetAll());
        // Cần gửi thêm danh sách Categories để hiển thị trong dropdown chọn danh mục
        model.addAttribute("nnhCategories", nnhCategoryService.nnhGetAll());
        return "admin/nnh-products";
    }
    @GetMapping("/products/{id}")
    public String nnhShowAdminProductDetail(@PathVariable Long id, Model model) {
        NnhProduct product = nnhProductService.nnhGetById(id);

        if (product == null) {
            return "redirect:/admin/products"; // Không thấy thì về danh sách
        }

        model.addAttribute("p", product); // Gửi object 'p' sang View
        return "admin/nnh-product-detail";
    }

    @GetMapping("/users")
    public String nnhShowUsers(Model model) {
        model.addAttribute("nnhUsers", nnhUserService.nnhGetAllUsersForAdmin());
        // Gửi thêm danh sách quyền để hiển thị trong Dropdown (Admin/User)
        model.addAttribute("nnhRoles", nnhRoleRepository.findAll());
        return "admin/nnh-users";
    }
    @GetMapping("/orders")
    public String nnhShowOrders(Model model) {
        model.addAttribute("nnhOrders", nnhOrderService.nnhGetAllOrders());
        return "admin/nnh-orders";
    }
    @GetMapping("/orders/{id}")
    public String nnhShowAdminOrderDetail(@PathVariable Long id, Model model) {
        NnhOrder order = nnhOrderService.nnhGetOrderById(id);

        if (order == null) {
            return "redirect:/admin/orders"; // Không tìm thấy thì quay về danh sách
        }

        model.addAttribute("nnhOrder", order);
        return "admin/nnh-order-detail"; // Trả về file HTML chi tiết
    }
}