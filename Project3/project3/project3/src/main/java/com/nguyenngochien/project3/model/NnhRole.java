package com.nguyenngochien.project3.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "nnh_roles")
@Data
public class NnhRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nnh_id")
    private Long nnhId;

    @Column(name = "nnh_name", nullable = false)
    private String nnhName; // Ví dụ: ROLE_ADMIN, ROLE_USER
}