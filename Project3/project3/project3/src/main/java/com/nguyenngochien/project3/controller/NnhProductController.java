package com.nguyenngochien.project3.controller;

import com.nguyenngochien.project3.model.NnhProduct;
import com.nguyenngochien.project3.service.NnhProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nnh-products")
public class NnhProductController {

    @Autowired
    private NnhProductService nnhProductService;

    @GetMapping
    public List<NnhProduct> nnhGetList() {
        // SỬA: nnhGetAllProducts() -> nnhGetAll()
        return nnhProductService.nnhGetAll();
    }

    @PostMapping
    public NnhProduct nnhCreate(@RequestBody NnhProduct product) {
        // SỬA: nnhSaveProduct() -> nnhSave()
        return nnhProductService.nnhSave(product);
    }

    @GetMapping("/search")
    public List<NnhProduct> nnhSearch(@RequestParam String keyword) {
        // SỬA: nnhSearchProducts() -> nnhSearch()
        return nnhProductService.nnhSearch(keyword);
    }
}