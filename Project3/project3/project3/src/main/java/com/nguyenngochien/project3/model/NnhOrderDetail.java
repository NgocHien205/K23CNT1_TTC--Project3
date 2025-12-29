package com.nguyenngochien.project3.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "nnh_order_details")
@Data
public class NnhOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nnh_id")
    private Long nnhId;

    @ManyToOne
    @JoinColumn(name = "nnh_order_id")
    @JsonIgnore
    private NnhOrder nnhOrder;

    @ManyToOne
    @JoinColumn(name = "nnh_product_id")
    private NnhProduct nnhProduct;

    @Column(name = "nnh_quantity")
    private Integer nnhQuantity;

    @Column(name = "nnh_price_at_purchase")
    private Double nnhPriceAtPurchase; // Lưu giá tại thời điểm mua
}