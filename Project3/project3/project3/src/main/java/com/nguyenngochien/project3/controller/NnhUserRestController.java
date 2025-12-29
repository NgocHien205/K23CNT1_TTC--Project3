package com.nguyenngochien.project3.controller;

import com.nguyenngochien.project3.model.NnhUser;
import com.nguyenngochien.project3.service.NnhUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nnh-users")
public class NnhUserRestController {

    @Autowired
    private NnhUserService nnhUserService;

    // API Cập nhật thông tin
    @PutMapping("/{id}/profile")
    public ResponseEntity<?> nnhUpdateProfile(@PathVariable Long id, @RequestBody NnhUser userDetails) {
        NnhUser updatedUser = nnhUserService.nnhUpdateProfile(id, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    // API Đổi mật khẩu
    @PutMapping("/{id}/password")
    public ResponseEntity<?> nnhChangePassword(@PathVariable Long id, @RequestBody NnhPasswordRequest passRequest) {
        boolean result = nnhUserService.nnhChangePassword(id, passRequest.getOldPass(), passRequest.getNewPass());
        if (result) {
            return ResponseEntity.ok("Đổi mật khẩu thành công!");
        } else {
            return ResponseEntity.badRequest().body("Mật khẩu cũ không chính xác!");
        }
    }

    // DTO hứng dữ liệu đổi pass
    @Data
    static class NnhPasswordRequest {
        private String oldPass;
        private String newPass;
    }
}