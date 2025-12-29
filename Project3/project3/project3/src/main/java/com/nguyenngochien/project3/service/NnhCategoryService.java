package com.nguyenngochien.project3.service;

import com.nguyenngochien.project3.model.NnhCategory;
import com.nguyenngochien.project3.repository.NnhCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NnhCategoryService {
    @Autowired
    private NnhCategoryRepository nnhCategoryRepository;

    public List<NnhCategory> nnhGetAll() {
        return nnhCategoryRepository.findAll();
    }

    public NnhCategory nnhSave(NnhCategory category) {
        return nnhCategoryRepository.save(category);
    }

    public void nnhDelete(Long id) {
        nnhCategoryRepository.deleteById(id);
    }

    public NnhCategory nnhGetById(Long id) {
        return nnhCategoryRepository.findById(id).orElse(null);
    }
}