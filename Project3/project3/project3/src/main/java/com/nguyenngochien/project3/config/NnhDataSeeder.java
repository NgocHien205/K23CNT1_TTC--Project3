package com.nguyenngochien.project3.config;

import com.nguyenngochien.project3.model.*;
import com.nguyenngochien.project3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class NnhDataSeeder implements CommandLineRunner {

    @Autowired
    private NnhRoleRepository roleRepo;
    @Autowired
    private NnhUserRepository userRepo;
    @Autowired
    private NnhCategoryRepository categoryRepo;
    @Autowired
    private NnhProductRepository productRepo;

    @Override
    public void run(String... args) throws Exception {
        // 1. Tạo Roles (Nếu chưa có)
        if (roleRepo.count() == 0) {
            NnhRole adminRole = new NnhRole();
            adminRole.setNnhName("ROLE_ADMIN");

            NnhRole userRole = new NnhRole();
            userRole.setNnhName("ROLE_USER");

            roleRepo.saveAll(Arrays.asList(adminRole, userRole));
        }

        // 2. Tạo Users (1 Admin, 1 User thường)
        if (userRepo.count() == 0) {
            NnhRole roleAdmin = roleRepo.findByNnhName("ROLE_ADMIN").get();
            NnhRole roleUser = roleRepo.findByNnhName("ROLE_USER").get();

            // Tài khoản Admin
            NnhUser admin = new NnhUser();
            admin.setNnhUsername("admin");
            admin.setNnhPassword("123456"); // Lưu text thường vì bạn đang tắt mã hóa
            admin.setNnhFullName("Quản Trị Viên");
            admin.setNnhEmail("admin@shop.com");
            admin.setNnhRole(roleAdmin);

            // Tài khoản User khách hàng
            NnhUser client = new NnhUser();
            client.setNnhUsername("khachhang");
            client.setNnhPassword("123456");
            client.setNnhFullName("Nguyễn Văn A");
            client.setNnhEmail("khach@shop.com");
            client.setNnhRole(roleUser);

            userRepo.saveAll(Arrays.asList(admin, client));
        }

        // 3. Tạo Danh mục (Categories)
        if (categoryRepo.count() == 0) {
            NnhCategory ao = new NnhCategory();
            ao.setNnhName("Áo Nam");
            ao.setNnhDescription("Các loại áo thun, sơ mi nam");

            NnhCategory quan = new NnhCategory();
            quan.setNnhName("Quần Jeans");
            quan.setNnhDescription("Quần bò các loại");

            categoryRepo.saveAll(Arrays.asList(ao, quan));
        }

        // 4. Tạo Sản phẩm (Products)
        if (productRepo.count() == 0) {
            NnhCategory catAo = categoryRepo.findAll().get(0); // Lấy category đầu tiên
            NnhCategory catQuan = categoryRepo.findAll().get(1);

            NnhProduct p1 = new NnhProduct();
            p1.setNnhName("Áo Thun Basic Trắng");
            p1.setNnhPrice(150000.0);
            p1.setNnhStock(100);
            p1.setNnhImageUrl("https://via.placeholder.com/150");
            p1.setNnhCategory(catAo);

            NnhProduct p2 = new NnhProduct();
            p2.setNnhName("Áo Sơ Mi Công Sở");
            p2.setNnhPrice(350000.0);
            p2.setNnhStock(50);
            p2.setNnhImageUrl("https://via.placeholder.com/150");
            p2.setNnhCategory(catAo);

            NnhProduct p3 = new NnhProduct();
            p3.setNnhName("Quần Jean Rách Gối");
            p3.setNnhPrice(450000.0);
            p3.setNnhStock(30);
            p3.setNnhImageUrl("https://via.placeholder.com/150");
            p3.setNnhCategory(catQuan);

            productRepo.saveAll(Arrays.asList(p1, p2, p3));
        }

        System.out.println(">>> ĐÃ KHỞI TẠO DỮ LIỆU MẪU THÀNH CÔNG! <<<");
    }
}