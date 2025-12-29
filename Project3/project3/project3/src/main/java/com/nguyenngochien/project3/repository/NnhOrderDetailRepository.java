package com.nguyenngochien.project3.repository;

import com.nguyenngochien.project3.model.NnhOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NnhOrderDetailRepository extends JpaRepository<NnhOrderDetail, Long> {
    // Thông thường chỉ cần các hàm CRUD cơ bản để lưu chi tiết hóa đơn
}
