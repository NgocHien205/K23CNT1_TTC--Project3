package com.nguyenngochien.project3.service;

import com.nguyenngochien.project3.model.*;
import com.nguyenngochien.project3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying; // Import mới
import org.springframework.data.jpa.repository.Query;     // Import mới
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NnhOrderService {

    @Autowired
    private NnhOrderRepository nnhOrderRepository;
    @Autowired
    private NnhOrderDetailRepository nnhOrderDetailRepository;
    @Autowired
    private NnhCartService nnhCartService;
    @Autowired
    private NnhProductRepository nnhProductRepository;

    @Transactional // Đảm bảo tính toàn vẹn: Mọi bước thành công mới commit, lỗi thì rollback hết
    public NnhOrder nnhCheckout(Long userId) {
        // 1. Lấy giỏ hàng
        NnhCart cart = nnhCartService.nnhGetCartByUser(userId);
        if (cart == null || cart.getNnhCartDetails().isEmpty()) {
            throw new RuntimeException("Giỏ hàng trống!");
        }

        // 2. Tạo đơn hàng (Order)
        NnhOrder order = new NnhOrder();
        order.setNnhUser(cart.getNnhUser());
        order.setNnhOrderDate(new java.util.Date());
        order.setNnhStatus("PENDING");
        order = nnhOrderRepository.save(order); // Lưu để có ID

        double totalAmount = 0;

        // 3. Duyệt qua từng sản phẩm để tạo OrderDetail và Trừ kho
        for (NnhCartDetail cartDetail : cart.getNnhCartDetails()) {
            NnhProduct product = cartDetail.getNnhProduct();

            // Kiểm tra tồn kho
            if (product.getNnhStock() < cartDetail.getNnhQuantity()) {
                throw new RuntimeException("Sản phẩm " + product.getNnhName() + " không đủ hàng!");
            }

            // Trừ kho
            product.setNnhStock(product.getNnhStock() - cartDetail.getNnhQuantity());
            nnhProductRepository.save(product);

            // Tạo chi tiết đơn
            NnhOrderDetail orderDetail = new NnhOrderDetail();
            orderDetail.setNnhOrder(order);
            orderDetail.setNnhProduct(product);
            orderDetail.setNnhQuantity(cartDetail.getNnhQuantity());
            orderDetail.setNnhPriceAtPurchase(product.getNnhPrice());
            nnhOrderDetailRepository.save(orderDetail);

            totalAmount += (product.getNnhPrice() * cartDetail.getNnhQuantity());
        }

        // 4. Cập nhật tổng tiền
        order.setNnhTotalAmount(totalAmount);
        nnhOrderRepository.save(order);

        // 5. QUAN TRỌNG: XÓA SẠCH GIỎ HÀNG SAU KHI MUA
        nnhCartService.nnhClearCart(cart.getNnhId());

        return order;
    }

    // Lấy danh sách đơn hàng của user
    public List<NnhOrder> nnhGetOrdersByUser(Long userId) {
        return nnhOrderRepository.findByNnhUser_NnhId(userId);
    }
    // Thêm hàm tìm đơn hàng theo ID (để xem chi tiết)
    public NnhOrder nnhGetOrderById(Long id) {
        return nnhOrderRepository.findById(id).orElse(null);
    }

    // Thêm hàm xóa đơn hàng
    public void nnhDeleteOrder(Long id) {
        nnhOrderRepository.deleteById(id);
    }

    // Hàm lấy tất cả đơn (dùng cho Admin View)
    public List<NnhOrder> nnhGetAllOrders() {
        // Nên sắp xếp đơn mới nhất lên đầu
        return nnhOrderRepository.findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC, "nnhOrderDate"));
    }
}