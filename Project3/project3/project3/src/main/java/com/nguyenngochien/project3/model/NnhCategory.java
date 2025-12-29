package com.nguyenngochien.project3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "nnh_categories")
@Data
public class NnhCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nnh_id")
    private Long nnhId;

    @Column(name = "nnh_name", nullable = false)
    private String nnhName;

    @Column(name = "nnh_description")
    private String nnhDescription;

    // Quan hệ: 1 Danh mục có nhiều sản phẩm (Để list ra cho dễ)
    // json ignore để tránh lỗi vòng lặp khi convert sang JSON
    @OneToMany(mappedBy = "nnhCategory")
    @JsonIgnore // <--- THÊM DÒNG NÀY: Khi xem danh mục, không cần list hết sản phẩm ra JSON làm gì (nặng và lỗi)
    private List<NnhProduct> nnhProducts;
}
