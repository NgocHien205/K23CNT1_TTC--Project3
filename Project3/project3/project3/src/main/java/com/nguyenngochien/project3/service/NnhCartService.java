package com.nguyenngochien.project3.service;

import com.nguyenngochien.project3.model.*;
import com.nguyenngochien.project3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NnhCartService {

    @Autowired
    private NnhCartRepository nnhCartRepository;
    @Autowired
    private NnhCartDetailRepository nnhCartDetailRepository;
    @Autowired
    private NnhProductRepository nnhProductRepository;
    @Autowired
    private NnhUserRepository nnhUserRepository;

    public NnhCart nnhGetCartByUser(Long userId) {
        return nnhCartRepository.findByNnhUser_NnhId(userId);
    }

    @Transactional
    public NnhCart nnhAddToCart(Long userId, Long productId, int quantity) {
        NnhCart cart = nnhCartRepository.findByNnhUser_NnhId(userId);

        // 1. Tạo giỏ nếu chưa có
        if (cart == null) {
            cart = new NnhCart();
            NnhUser user = nnhUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            cart.setNnhUser(user);
            cart = nnhCartRepository.save(cart);
        }

        NnhProduct product = nnhProductRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        // 2. Tìm xem sản phẩm đã có trong giỏ chưa để cộng dồn
        NnhCartDetail existingDetail = null;
        if (cart.getNnhCartDetails() != null) {
            for (NnhCartDetail detail : cart.getNnhCartDetails()) {
                if (detail.getNnhProduct().getNnhId().equals(productId)) {
                    existingDetail = detail;
                    break;
                }
            }
        }

        if (existingDetail != null) {
            existingDetail.setNnhQuantity(existingDetail.getNnhQuantity() + quantity);
            nnhCartDetailRepository.save(existingDetail);
        } else {
            NnhCartDetail newDetail = new NnhCartDetail();
            newDetail.setNnhCart(cart);
            newDetail.setNnhProduct(product);
            newDetail.setNnhQuantity(quantity);
            nnhCartDetailRepository.save(newDetail);
        }
        return nnhCartRepository.findById(cart.getNnhId()).get();
    }

    // CẬP NHẬT: Hàm xóa giỏ hàng gọi method Query mới
    @Transactional
    public void nnhClearCart(Long cartId) {
        // Gọi hàm mới dùng @Query
        nnhCartDetailRepository.nnhDeleteAllByCartId(cartId);
    }
}