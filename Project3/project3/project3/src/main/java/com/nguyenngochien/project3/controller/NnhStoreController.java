package com.nguyenngochien.project3.controller;

import com.nguyenngochien.project3.model.*;
import com.nguyenngochien.project3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NnhStoreController {

    @Autowired private NnhProductService nnhProductService;
    @Autowired private NnhCategoryService nnhCategoryService;
    @Autowired private NnhCartService nnhCartService; // Inject Service Cart;
    @Autowired private NnhOrderService nnhOrderService;
    @Autowired private NnhUserService nnhUserService;

    // Trang chủ (Hiển thị sản phẩm)
    @GetMapping("/")
    public String nnhHome(Model model, @RequestParam(required = false) String keyword) {

        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("nnhProducts", nnhProductService.nnhSearch(keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("nnhProducts", nnhProductService.nnhGetAll());
        }

        // Gửi thêm danh mục để làm menu lọc (nếu cần)
        model.addAttribute("nnhCategories", nnhCategoryService.nnhGetAll());

        return "client/nnh-home"; // Trỏ đến file HTML trang chủ
    }
    // Trang xem Giỏ hàng
    // SỬA HÀM NÀY
    @GetMapping("/cart")
    public String nnhShowCart(@RequestParam(required = false) Long userId, Model model) {
        // 1. Kiểm tra nếu không có userId -> Bắt đăng nhập
        if (userId == null) {
            return "redirect:/login";
        }

        // 2. Lấy giỏ hàng theo userId truyền vào
        NnhCart cart = nnhCartService.nnhGetCartByUser(userId);

        double totalAmount = 0;
        if (cart != null && cart.getNnhCartDetails() != null) {
            for (NnhCartDetail detail : cart.getNnhCartDetails()) {
                totalAmount += detail.getNnhProduct().getNnhPrice() * detail.getNnhQuantity();
            }
        }

        model.addAttribute("nnhCart", cart);
        model.addAttribute("nnhTotalAmount", totalAmount);

        // Gửi userId xuống view để nếu F5 hoặc thao tác tiếp vẫn giữ được ID
        model.addAttribute("currentUserId", userId);

        return "client/nnh-cart";
    }

    // Trang thông báo đặt hàng thành công
    @GetMapping("/order-success")
    public String nnhOrderSuccess(@RequestParam(required = false) Long orderId, Model model) {
        // Gửi ID đơn hàng sang View để tạo link "Xem chi tiết"
        model.addAttribute("nnhOrderId", orderId);
        return "client/nnh-order-success";
    }
    // 1. TRANG LỊCH SỬ ĐƠN HÀNG
    // 1. SỬA: Trang lịch sử đơn hàng (Nhận userId từ param)
    @GetMapping("/my-orders")
    public String nnhShowOrderHistory(@RequestParam(required = false) Long userId, Model model) {
        // Nếu không có userId trên URL, trả về trang yêu cầu đăng nhập hoặc rỗng
        if (userId == null) {
            return "redirect:/login";
        }

        List<NnhOrder> orders = nnhOrderService.nnhGetOrdersByUser(userId);

        // Sắp xếp mới nhất lên đầu
        orders.sort((o1, o2) -> o2.getNnhOrderDate().compareTo(o1.getNnhOrderDate()));

        model.addAttribute("nnhOrders", orders);
        model.addAttribute("currentUserId", userId); // Gửi lại ID để dùng trong view
        return "client/nnh-order-history";
    }

    // 2. SỬA: Trang chi tiết đơn hàng (Nhận userId từ param để verify)
    @GetMapping("/my-orders/{id}")
    public String nnhShowOrderDetail(@PathVariable Long id, @RequestParam(required = false) Long userId, Model model) {
        if (userId == null) return "redirect:/login";

        NnhOrder order = nnhOrderService.nnhGetOrderById(id);

        // Bảo mật: Kiểm tra đơn hàng có đúng của User này không
        if (order == null || !order.getNnhUser().getNnhId().equals(userId)) {
            return "redirect:/my-orders?userId=" + userId;
        }

        model.addAttribute("nnhOrder", order);
        model.addAttribute("currentUserId", userId);
        return "client/nnh-order-detail";
    }
    // THÊM: Trang chi tiết sản phẩm
    @GetMapping("/product/{id}")
    public String nnhProductDetail(@PathVariable Long id, Model model) {
        // 1. Lấy thông tin sản phẩm hiện tại
        NnhProduct product = nnhProductService.nnhGetById(id);

        // Nếu không tìm thấy sản phẩm, quay về trang chủ
        if (product == null) return "redirect:/";

        model.addAttribute("p", product);

        // 2. Lấy danh sách sản phẩm liên quan
        Long catId = (product.getNnhCategory() != null) ? product.getNnhCategory().getNnhId() : null;
        model.addAttribute("nnhRelatedProducts", nnhProductService.nnhGetRelatedProducts(catId, id));

        return "client/nnh-product-detail";
    }
    // THÊM: Trang Đăng nhập
    @GetMapping("/login")
    public String nnhShowLoginPage() {
        return "client/nnh-login";
    }

    // THÊM: Trang Đăng ký
    @GetMapping("/register")
    public String nnhShowRegisterPage() {
        return "client/nnh-register";
    }
    // THÊM: Trang quản lý tài khoản cá nhân
    @GetMapping("/profile")
    public String nnhShowProfilePage(@RequestParam(required = false) Long userId) {
        // Nếu muốn bảo mật hơn thì check userId null ở đây rồi redirect login
        if (userId == null) return "redirect:/login";
        return "client/nnh-profile";
    }
    // THÊM: Trang điền thông tin thanh toán
    @GetMapping("/checkout")
    public String nnhShowCheckoutPage(@RequestParam(required = false) Long userId, Model model) {
        if (userId == null) return "redirect:/login";

        // Lấy thông tin user mới nhất để điền sẵn vào form (nếu họ đã từng nhập)
        NnhUser user = nnhUserService.nnhGetUserById(userId); // Bạn cần đảm bảo UserService có hàm getById public

        // Lấy giỏ hàng để hiển thị lại tổng tiền
        NnhCart cart = nnhCartService.nnhGetCartByUser(userId);

        // Tính lại tổng tiền
        double totalAmount = 0;
        if (cart != null && cart.getNnhCartDetails() != null) {
            for (NnhCartDetail detail : cart.getNnhCartDetails()) {
                totalAmount += detail.getNnhProduct().getNnhPrice() * detail.getNnhQuantity();
            }
        }

        model.addAttribute("user", user);
        model.addAttribute("nnhTotalAmount", totalAmount);
        model.addAttribute("currentUserId", userId);

        return "client/nnh-checkout";
    }
}