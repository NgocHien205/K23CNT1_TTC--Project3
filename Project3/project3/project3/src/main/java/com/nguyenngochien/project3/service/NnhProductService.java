package com.nguyenngochien.project3.service;

import com.nguyenngochien.project3.model.NnhProduct;
import com.nguyenngochien.project3.repository.NnhProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NnhProductService {

    @Autowired
    private NnhProductRepository nnhProductRepository;

    public List<NnhProduct> nnhGetAll() {
        return nnhProductRepository.findAll();
    }

    public NnhProduct nnhGetById(Long id) {
        return nnhProductRepository.findById(id).orElse(null);
    }

    public NnhProduct nnhSave(NnhProduct product) {
        return nnhProductRepository.save(product);
    }

    public void nnhDelete(Long id) {
        nnhProductRepository.deleteById(id);
    }

    // Tìm kiếm
    public List<NnhProduct> nnhSearch(String keyword) {
        return nnhProductRepository.findByNnhNameContaining(keyword);
    }
    // THÊM: Lấy sản phẩm liên quan
    public List<NnhProduct> nnhGetRelatedProducts(Long categoryId, Long currentProductId) {
        if (categoryId == null) return List.of(); // Trả về rỗng nếu sp ko có danh mục

        // Gọi Repo
        List<NnhProduct> products = nnhProductRepository.findByNnhCategory_NnhIdAndNnhIdNot(categoryId, currentProductId);

        // Giới hạn lấy 4 sản phẩm thôi cho đẹp giao diện
        if (products.size() > 4) {
            return products.subList(0, 4);
        }
        return products;
    }
}