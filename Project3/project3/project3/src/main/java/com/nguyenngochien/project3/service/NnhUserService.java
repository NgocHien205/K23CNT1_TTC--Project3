package com.nguyenngochien.project3.service;

import com.nguyenngochien.project3.model.NnhRole;
import com.nguyenngochien.project3.model.NnhUser;
import com.nguyenngochien.project3.repository.NnhRoleRepository;
import com.nguyenngochien.project3.repository.NnhUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class NnhUserService {

    @Autowired
    private NnhUserRepository nnhUserRepository;

    @Autowired
    private NnhRoleRepository nnhRoleRepository;

    public NnhUser nnhRegisterUser(NnhUser user) {
        if (nnhUserRepository.existsByNnhUsername(user.getNnhUsername())) {
            throw new RuntimeException("Username đã tồn tại!");
        }

        if (user.getNnhRole() == null) {
            NnhRole defaultRole = nnhRoleRepository.findByNnhName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Role User chưa được tạo trong DB"));
            user.setNnhRole(defaultRole);
        }

        // SỬA: Lưu trực tiếp mật khẩu dạng text, không mã hóa nữa
        // user.setNnhPassword(nnhPasswordEncoder.encode(user.getNnhPassword()));
        // Giữ nguyên password người dùng nhập:
        user.setNnhPassword(user.getNnhPassword());

        return nnhUserRepository.save(user);
    }
    public java.util.List<NnhUser> nnhGetAllUsersForAdmin() {
        return nnhUserRepository.findAll();
    }

    public void nnhDeleteUser(Long id) {
        nnhUserRepository.deleteById(id);
    }
    // Thêm hàm lấy 1 user để hiển thị lên form sửa
    public NnhUser nnhGetUserById(Long id) {
        return nnhUserRepository.findById(id).orElse(null);
    }

    // Hàm Save dùng cho cả Thêm mới và Cập nhật (Admin dùng)
    public NnhUser nnhSaveUserByAdmin(NnhUser user) {
        // Lưu ý: Ở môi trường Dev này ta không mã hóa password lại nếu admin sửa thông tin khác
        // Logic thực tế sẽ phức tạp hơn, nhưng ở đây ta save thẳng.
        return nnhUserRepository.save(user);
    }

    public NnhUser nnhFindByUsername(String username) {
        return nnhUserRepository.findByNnhUsername(username).orElse(null);
    }
    // 1. Cập nhật thông tin cá nhân (Tên, Email)
    public NnhUser nnhUpdateProfile(Long userId, NnhUser updatedInfo) {
        NnhUser user = nnhUserRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setNnhFullName(updatedInfo.getNnhFullName());
            user.setNnhEmail(updatedInfo.getNnhEmail());
            user.setNnhPhoneNumber(updatedInfo.getNnhPhoneNumber());
            user.setNnhAddress(updatedInfo.getNnhAddress());
            return nnhUserRepository.save(user);
        }
        return null;
    }

    // 2. Đổi mật khẩu
    public boolean nnhChangePassword(Long userId, String oldPass, String newPass) {
        NnhUser user = nnhUserRepository.findById(userId).orElse(null);

        // So sánh mật khẩu cũ (dạng text thường do đang tắt mã hóa)
        if (user != null && user.getNnhPassword().equals(oldPass)) {
            user.setNnhPassword(newPass);
            nnhUserRepository.save(user);
            return true; // Đổi thành công
        }
        return false; // Mật khẩu cũ sai hoặc user không tồn tại
    }
}