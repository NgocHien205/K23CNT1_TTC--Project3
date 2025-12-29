package com.nguyenngochien.project3.controller;

import com.nguyenngochien.project3.repository.NnhOrderRepository;
import com.nguyenngochien.project3.repository.NnhProductRepository;
import com.nguyenngochien.project3.repository.NnhUserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/dashboard")
public class NnhDashboardController {

    @Autowired
    private NnhProductRepository nnhProductRepository;
    @Autowired
    private NnhUserRepository nnhUserRepository;
    @Autowired
    private NnhOrderRepository nnhOrderRepository;

    // API trả về số liệu thống kê tổng hợp
    @GetMapping("/stats")
    public NnhDashboardStats nnhGetStats() {
        NnhDashboardStats stats = new NnhDashboardStats();

        stats.setNnhTotalProducts(nnhProductRepository.count());
        stats.setNnhTotalUsers(nnhUserRepository.count());
        stats.setNnhTotalOrders(nnhOrderRepository.count());

        // Tính tổng doanh thu (Logic đơn giản: cộng tổng tiền các đơn hàng)
        double totalRevenue = nnhOrderRepository.findAll().stream()
                .mapToDouble(order -> order.getNnhTotalAmount() != null ? order.getNnhTotalAmount() : 0)
                .sum();
        stats.setNnhTotalRevenue(totalRevenue);

        return stats;
    }

    // Class DTO nội bộ để trả về dữ liệu JSON
    @Data
    static class NnhDashboardStats {
        private long nnhTotalProducts;
        private long nnhTotalUsers;
        private long nnhTotalOrders;
        private double nnhTotalRevenue;
    }
}