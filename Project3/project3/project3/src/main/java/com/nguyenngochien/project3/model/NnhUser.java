package com.nguyenngochien.project3.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "nnh_users")
@Data
public class NnhUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nnh_id")
    private Long nnhId;

    @Column(name = "nnh_username", unique = true, nullable = false)
    private String nnhUsername;

    @Column(name = "nnh_password", nullable = false)
    private String nnhPassword;

    @Column(name = "nnh_full_name")
    private String nnhFullName;

    @Column(name = "nnh_email")
    private String nnhEmail;
    // ... các trường cũ ...

    @Column(name = "nnh_phone_number")
    private String nnhPhoneNumber;

    @Column(name = "nnh_address")
    private String nnhAddress;

    // Quan hệ: Nhiều user có thể cùng 1 role
    @ManyToOne
    @JoinColumn(name = "nnh_role_id")
    private NnhRole nnhRole;
}