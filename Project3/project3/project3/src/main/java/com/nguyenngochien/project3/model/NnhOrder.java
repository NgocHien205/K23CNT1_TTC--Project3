package com.nguyenngochien.project3.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nnh_orders")
@Data
public class NnhOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nnh_id")
    private Long nnhId;

    // Quan hệ: Đơn hàng thuộc về 1 user
    @ManyToOne
    @JoinColumn(name = "nnh_user_id")
    private NnhUser nnhUser;

    @Column(name = "nnh_order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nnhOrderDate = new Date();

    @Column(name = "nnh_total_amount")
    private Double nnhTotalAmount;

    @Column(name = "nnh_status")
    private String nnhStatus; // PENDING, SHIPPED, CANCELLED

    // Quan hệ: 1 Đơn hàng có nhiều chi tiết
    @OneToMany(mappedBy = "nnhOrder", cascade = CascadeType.ALL)
    private List<NnhOrderDetail> nnhOrderDetails;
}