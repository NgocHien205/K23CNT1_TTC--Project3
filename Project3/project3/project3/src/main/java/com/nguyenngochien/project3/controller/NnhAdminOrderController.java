package com.nguyenngochien.project3.controller;

import com.nguyenngochien.project3.model.NnhOrder;
import com.nguyenngochien.project3.repository.NnhOrderRepository;
import com.nguyenngochien.project3.service.NnhOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/nnh-orders")
public class NnhAdminOrderController {

    @Autowired
    private NnhOrderService nnhOrderService; // Đổi sang dùng Service cho chuẩn

    @Autowired
    private NnhOrderRepository nnhOrderRepository; // Hoặc giữ Repo nếu lười sửa Service wrapper

    // Lấy chi tiết 1 đơn hàng (Dùng cho Modal)
    @GetMapping("/{id}")
    public ResponseEntity<NnhOrder> nnhGetOrderDetail(@PathVariable Long id) {
        NnhOrder order = nnhOrderService.nnhGetOrderById(id);
        if (order != null) return ResponseEntity.ok(order);
        return ResponseEntity.notFound().build();
    }

    // Cập nhật trạng thái
    @PutMapping("/{id}/status")
    public ResponseEntity<NnhOrder> nnhUpdateStatus(@PathVariable Long id, @RequestParam String newStatus) {
        NnhOrder order = nnhOrderService.nnhGetOrderById(id);
        if (order == null) return ResponseEntity.notFound().build();

        order.setNnhStatus(newStatus);
        // Lưu lại thông qua repository
        return ResponseEntity.ok(nnhOrderRepository.save(order));
    }

    // Xóa đơn hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> nnhDeleteOrder(@PathVariable Long id) {
        nnhOrderService.nnhDeleteOrder(id);
        return ResponseEntity.ok().build();
    }
}