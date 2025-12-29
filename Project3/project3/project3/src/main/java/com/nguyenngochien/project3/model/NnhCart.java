package com.nguyenngochien.project3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nnh_carts")
@Data
public class NnhCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nnh_id")
    private Long nnhId;

    @OneToOne
    @JoinColumn(name = "nnh_user_id")
    private NnhUser nnhUser; // Bạn cần chắc chắn class NnhUser đã tồn tại

    @Column(name = "nnh_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nnhCreatedDate = new Date();

    // Quan hệ 1 giỏ hàng có nhiều chi tiết
    @OneToMany(mappedBy = "nnhCart", cascade = CascadeType.ALL)
    private List<NnhCartDetail> nnhCartDetails;
}