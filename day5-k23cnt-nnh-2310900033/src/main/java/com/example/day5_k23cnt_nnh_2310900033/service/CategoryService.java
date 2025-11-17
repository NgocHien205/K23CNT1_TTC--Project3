package com.example.day5_k23cnt_nnh_2310900033.service;



import com.example.day5_k23cnt_nnh_2310900033.entity.Category;
import com.example.day5_k23cnt_nnh_2310900033.repostiory.CategoryRepostiosy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepostiosy categoryRepostiosy;

    public CategoryService(CategoryRepostiosy categoryRepository) {
        this.categoryRepostiosy = categoryRepository;
    }

    // Lấy danh sách
    public List<Category> getAllCategories() {
        System.out.println(categoryRepostiosy.findAll());
        return categoryRepostiosy.findAll();
    }

    // Lấy category theo id
    public Optional<Category> getCategoryById(long id) {
        return categoryRepostiosy.findById(id);
    }

    // Cập nhật dữ liệu bảng category: create / update
    public Category saveCategory(Category category) {
        return categoryRepostiosy.save(category);
    }

    // Xóa category theo id
    public void deleteCategory(long id) {
        categoryRepostiosy.deleteById(id);
    }
}
