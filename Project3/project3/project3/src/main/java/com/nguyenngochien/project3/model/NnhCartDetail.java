package com.nguyenngochien.project3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "nnh_cart_details")
@Data
public class NnhCartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nnh_id")
    private Long nnhId;

    @ManyToOne
    @JoinColumn(name = "nnh_cart_id")
    @JsonIgnore
    private NnhCart nnhCart;

    @ManyToOne
    @JoinColumn(name = "nnh_product_id")
    private NnhProduct nnhProduct;

    @Column(name = "nnh_quantity")
    private Integer nnhQuantity;
}