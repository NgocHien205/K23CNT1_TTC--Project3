package com.nguyenngochien.project3.controller;

import com.nguyenngochien.project3.model.NnhCart;
import com.nguyenngochien.project3.service.NnhCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nnh-carts")
public class NnhCartController {

    @Autowired
    private NnhCartService nnhCartService;

    // Xem giỏ hàng của user
    @GetMapping("/{userId}")
    public NnhCart nnhViewCart(@PathVariable Long userId) {
        // SỬA: nnhGetCartByUserId() -> nnhGetCartByUser()
        return nnhCartService.nnhGetCartByUser(userId);
    }

    // Thêm vào giỏ hàng
    @PostMapping("/add")
    public NnhCart nnhAddToCart(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        // Hàm này tên vẫn đúng, giữ nguyên
        return nnhCartService.nnhAddToCart(userId, productId, quantity);
    }
}