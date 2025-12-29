package com.nguyenngochien.project3.repository;


import com.nguyenngochien.project3.model.NnhUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NnhUserRepository extends JpaRepository<NnhUser, Long> {
    // Dùng cho chức năng Đăng nhập (Login)
    Optional<NnhUser> findByNnhUsername(String nnhUsername);

    // Dùng để kiểm tra trùng lặp khi Đăng ký (Register)
    Boolean existsByNnhUsername(String nnhUsername);
    Boolean existsByNnhEmail(String nnhEmail);
}
