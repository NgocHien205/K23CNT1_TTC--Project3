package com.nguyenngochien.project3.repository;

import com.nguyenngochien.project3.model.NnhOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NnhOrderRepository extends JpaRepository<NnhOrder, Long> {

    // User: Xem lịch sử đơn hàng của chính mình
    List<NnhOrder> findByNnhUser_NnhId(Long nnhUserId);

    // Admin: Xem tất cả đơn hàng theo trạng thái (Vd: Lấy danh sách đơn PENDING để duyệt)
    List<NnhOrder> findByNnhStatus(String nnhStatus);

    // Admin: Thống kê đơn hàng trong khoảng ngày (Reporting)
    List<NnhOrder> findByNnhOrderDateBetween(java.util.Date startDate, java.util.Date endDate);
}
