package com.nguyenngochien.project3.controller;

import com.nguyenngochien.project3.model.NnhOrder;
import com.nguyenngochien.project3.model.NnhUser;
import com.nguyenngochien.project3.service.NnhOrderService;
import com.nguyenngochien.project3.service.NnhUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/nnh-orders")
public class NnhOrderController {

    @Autowired
    private NnhOrderService nnhOrderService;

    // API Thanh toán (Chuyển giỏ hàng thành đơn hàng)
    // URL: POST /api/nnh-orders/checkout?userId=2
    @PostMapping("/checkout")
    public ResponseEntity<?> nnhCheckout(@RequestParam Long userId) {
        try {
            NnhOrder order = nnhOrderService.nnhCheckout(userId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Ví dụ: Hết hàng, Giỏ trống
        }
    }
    @Data
    static class NnhCheckoutRequest {
        private Long userId;
        private String phone;
        private String address;
    }
    @Autowired private NnhUserService nnhUserService; // Inject thêm

    // API MỚI: Nhận thông tin giao hàng -> Cập nhật User -> Tạo đơn
    @PostMapping("/confirm-checkout")
    public ResponseEntity<?> nnhConfirmCheckout(@RequestBody NnhCheckoutRequest req) {
        try {
            // 1. Cập nhật thông tin SĐT/Địa chỉ cho User
            NnhUser currentUser = nnhUserService.nnhGetUserById(req.getUserId());
            if(currentUser != null) {
                currentUser.setNnhPhoneNumber(req.getPhone());
                currentUser.setNnhAddress(req.getAddress());
                nnhUserService.nnhSaveUserByAdmin(currentUser); // Hàm này save thẳng user, tái sử dụng được
            }

            // 2. Gọi hàm checkout cũ để tạo đơn
            NnhOrder order = nnhOrderService.nnhCheckout(req.getUserId());

            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}