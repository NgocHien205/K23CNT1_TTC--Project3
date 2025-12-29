package com.nguyenngochien.project3.repository;

import com.nguyenngochien.project3.model.NnhCartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying; // Import mới
import org.springframework.data.jpa.repository.Query;     // Import mới
import org.springframework.data.repository.query.Param;

@Repository
public interface NnhCartDetailRepository extends JpaRepository<NnhCartDetail, Long> {
    // Hỗ trợ xóa sản phẩm khỏi giỏ hàng
    void deleteByNnhId(Long nnhId);

    // CẬP NHẬT: Dùng Query để ép buộc xóa sạch chi tiết theo Cart ID
    @Modifying
    @Query("DELETE FROM NnhCartDetail d WHERE d.nnhCart.nnhId = :cartId")
    void nnhDeleteAllByCartId(@Param("cartId") Long cartId);
}