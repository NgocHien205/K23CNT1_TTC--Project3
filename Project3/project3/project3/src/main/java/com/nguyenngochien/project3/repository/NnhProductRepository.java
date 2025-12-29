package com.nguyenngochien.project3.repository;

import com.nguyenngochien.project3.model.NnhProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NnhProductRepository extends JpaRepository<NnhProduct, Long> {

    // User: Tìm kiếm sản phẩm theo tên (gần đúng)
    List<NnhProduct> findByNnhNameContaining(String keyword);

    // User: Lọc sản phẩm theo danh mục (Category ID)
    // Cú pháp: findBy + Tên biến quan hệ (NnhCategory) + _ + Tên thuộc tính con (NnhId)
    List<NnhProduct> findByNnhCategory_NnhId(Long nnhCategoryId);

    // User: Lọc sản phẩm theo khoảng giá
    // THÊM: Tìm sản phẩm cùng danh mục, trừ sản phẩm hiện tại (Top 4)
    // Cú pháp JPA: findBy + CategoryId + And + Id + Not
    List<NnhProduct> findByNnhCategory_NnhIdAndNnhIdNot(Long categoryId, Long excludeProductId);
}