package com.nguyenngochien.project3.controller;

import com.nguyenngochien.project3.model.NnhUser;
import com.nguyenngochien.project3.repository.NnhRoleRepository;
import com.nguyenngochien.project3.service.NnhUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/nnh-users")
public class NnhAdminUserController {

    @Autowired
    private NnhUserService nnhUserService;

    @Autowired
    private NnhRoleRepository nnhRoleRepository;

    // Lấy danh sách User
    @GetMapping
    public List<NnhUser> nnhGetAllUsers() {
        return nnhUserService.nnhGetAllUsersForAdmin();
    }

    // API Thêm mới User
    @PostMapping
    public ResponseEntity<?> nnhCreateUser(@RequestBody NnhUser user) {
        if (nnhUserService.nnhFindByUsername(user.getNnhUsername()) != null) {
            return ResponseEntity.badRequest().body("Username đã tồn tại");
        }
        // Hàm save của Service sẽ tự lưu cả SĐT và Địa chỉ nếu có trong request body
        NnhUser savedUser = nnhUserService.nnhSaveUserByAdmin(user);
        return ResponseEntity.ok(savedUser);
    }

    // API Cập nhật User (Đã thêm cập nhật SĐT và Địa chỉ)
    @PutMapping("/{id}")
    public ResponseEntity<?> nnhUpdateUser(@PathVariable Long id, @RequestBody NnhUser userDetails) {
        NnhUser user = nnhUserService.nnhGetUserById(id);
        if (user == null) return ResponseEntity.notFound().build();

        // Cập nhật các trường thông tin
        user.setNnhFullName(userDetails.getNnhFullName());
        user.setNnhEmail(userDetails.getNnhEmail());

        // Cập nhật SĐT và Địa chỉ
        user.setNnhPhoneNumber(userDetails.getNnhPhoneNumber());
        user.setNnhAddress(userDetails.getNnhAddress());

        // Nếu admin nhập password mới thì cập nhật
        if (userDetails.getNnhPassword() != null && !userDetails.getNnhPassword().isEmpty()) {
            user.setNnhPassword(userDetails.getNnhPassword());
        }

        // Cập nhật Role
        if (userDetails.getNnhRole() != null) {
            user.setNnhRole(userDetails.getNnhRole());
        }

        NnhUser updatedUser = nnhUserService.nnhSaveUserByAdmin(user);
        return ResponseEntity.ok(updatedUser);
    }

    // Xóa User
    @DeleteMapping("/{id}")
    public void nnhDeleteUser(@PathVariable Long id) {
        nnhUserService.nnhDeleteUser(id);
    }
}