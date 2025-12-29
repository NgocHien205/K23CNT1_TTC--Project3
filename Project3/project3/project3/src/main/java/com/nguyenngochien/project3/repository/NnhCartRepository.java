package com.nguyenngochien.project3.repository;

import com.nguyenngochien.project3.model.NnhCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NnhCartRepository extends JpaRepository<NnhCart, Long> {
    // Tìm giỏ hàng hiện tại của User
    NnhCart findByNnhUser_NnhId(Long nnhUserId);
}