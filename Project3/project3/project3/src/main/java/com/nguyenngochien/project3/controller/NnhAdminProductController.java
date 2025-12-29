package com.nguyenngochien.project3.controller;

import com.nguyenngochien.project3.model.NnhProduct;
import com.nguyenngochien.project3.service.NnhProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/nnh-products")
public class NnhAdminProductController {

    @Autowired
    private NnhProductService nnhProductService;

    // Lấy danh sách tất cả sản phẩm
    @GetMapping
    public List<NnhProduct> nnhGetAll() {
        return nnhProductService.nnhGetAll();
    }

    // Thêm mới sản phẩm
    @PostMapping
    public NnhProduct nnhCreate(@RequestBody NnhProduct product) {
        return nnhProductService.nnhSave(product);
    }

    // Cập nhật sản phẩm
    @PutMapping("/{id}")
    public ResponseEntity<NnhProduct> nnhUpdate(@PathVariable Long id, @RequestBody NnhProduct productDetails) {
        NnhProduct product = nnhProductService.nnhGetById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        // Cập nhật thông tin
        product.setNnhName(productDetails.getNnhName());
        product.setNnhPrice(productDetails.getNnhPrice());
        product.setNnhStock(productDetails.getNnhStock());
        product.setNnhImageUrl(productDetails.getNnhImageUrl());
        product.setNnhCategory(productDetails.getNnhCategory());

        NnhProduct updatedProduct = nnhProductService.nnhSave(product);
        return ResponseEntity.ok(updatedProduct);
    }

    // Xóa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> nnhDelete(@PathVariable Long id) {
        nnhProductService.nnhDelete(id);
        return ResponseEntity.ok().build();
    }
}