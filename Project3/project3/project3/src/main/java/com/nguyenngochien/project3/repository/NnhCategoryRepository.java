package com.nguyenngochien.project3.repository;


import com.nguyenngochien.project3.model.NnhCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NnhCategoryRepository extends JpaRepository<NnhCategory, Long> {
    // Admin: Kiểm tra xem tên danh mục đã tồn tại chưa khi thêm mới
    Boolean existsByNnhName(String nnhName);
}
