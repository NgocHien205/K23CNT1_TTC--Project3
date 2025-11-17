package com.example.day5_k23cnt_nnh_2310900033.repostiory;




import com.example.day5_k23cnt_nnh_2310900033.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface CategoryRepostiosy extends JpaRepository<Category, Long> {


}
