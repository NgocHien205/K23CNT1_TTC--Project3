package com.nguyenngochien.project3.controller;

import com.nguyenngochien.project3.model.NnhCategory;
import com.nguyenngochien.project3.service.NnhCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/nnh-categories")
public class NnhAdminCategoryController {

    @Autowired
    private NnhCategoryService nnhCategoryService;

    @GetMapping
    public List<NnhCategory> nnhGetAll() {
        return nnhCategoryService.nnhGetAll();
    }

    @PostMapping
    public NnhCategory nnhCreate(@RequestBody NnhCategory category) {
        return nnhCategoryService.nnhSave(category);
    }

    @PutMapping("/{id}")
    public NnhCategory nnhUpdate(@PathVariable Long id, @RequestBody NnhCategory categoryDetails) {
        NnhCategory category = nnhCategoryService.nnhGetById(id);
        if(category != null) {
            category.setNnhName(categoryDetails.getNnhName());
            category.setNnhDescription(categoryDetails.getNnhDescription());
            return nnhCategoryService.nnhSave(category);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void nnhDelete(@PathVariable Long id) {
        nnhCategoryService.nnhDelete(id);
    }
}