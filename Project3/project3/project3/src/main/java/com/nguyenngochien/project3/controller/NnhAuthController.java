package com.nguyenngochien.project3.controller;

import com.nguyenngochien.project3.model.NnhUser;
import com.nguyenngochien.project3.repository.NnhUserRepository;
import com.nguyenngochien.project3.service.NnhUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class NnhAuthController {

    @Autowired
    private NnhUserService nnhUserService;

    @Autowired
    private NnhUserRepository nnhUserRepository;

    // API Đăng ký
    @PostMapping("/register")
    public ResponseEntity<?> nnhRegister(@RequestBody NnhUser user) {
        try {
            NnhUser newUser = nnhUserService.nnhRegisterUser(user);
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // API Đăng nhập ĐƠN GIẢN (So sánh chuỗi text)
    @PostMapping("/login")
    public ResponseEntity<?> nnhLogin(@RequestBody NnhLoginRequest loginRequest) {
        // 1. Tìm user trong DB
        NnhUser user = nnhUserRepository.findByNnhUsername(loginRequest.getUsername())
                .orElse(null);

        // 2. Kiểm tra user tồn tại và mật khẩu khớp nhau (so sánh string thường)
        if (user != null && user.getNnhPassword().equals(loginRequest.getPassword())) {
            // Đăng nhập thành công -> Trả về thông tin user (để lấy ID dùng cho Cart/Order)
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().body("Sai tài khoản hoặc mật khẩu!");
        }
    }

    @Data
    static class NnhLoginRequest {
        private String username;
        private String password;
    }
}