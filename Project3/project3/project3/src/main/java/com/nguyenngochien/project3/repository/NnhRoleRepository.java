package com.nguyenngochien.project3.repository;


import com.nguyenngochien.project3.model.NnhRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NnhRoleRepository extends JpaRepository<NnhRole, Long> {
    // Tìm quyền theo tên (Ví dụ: dùng để gán quyền ROLE_USER mặc định khi đăng ký)
    Optional<NnhRole> findByNnhName(String nnhName);
}
