package com.nguyenngochien.project3.model;

import jakarta.persistence.*;
import lombok.Data; // Sử dụng Lombok để tự tạo getter/setter

@Entity
@Table(name = "nnh_products")
@Data
public class NnhProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nnh_id")
    private Long nnhId;

    @Column(name = "nnh_name", nullable = false)
    private String nnhName;

    @Column(name = "nnh_price")
    private Double nnhPrice;

    @Column(name = "nnh_stock")
    private Integer nnhStock;

    @Column(name = "nnh_image_url")
    private String nnhImageUrl;

    // Quan hệ với Category (Giả sử bạn đã tạo class NnhCategory tương tự)
    @ManyToOne
    @JoinColumn(name = "nnh_category_id")
    private NnhCategory nnhCategory;
}