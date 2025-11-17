package com.example.day5_k23cnt_nnh_2310900033.repostiory;

import com.example.day5_k23cnt_nnh_2310900033.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepostiosy extends JpaRepository<Product, Long> {

}
