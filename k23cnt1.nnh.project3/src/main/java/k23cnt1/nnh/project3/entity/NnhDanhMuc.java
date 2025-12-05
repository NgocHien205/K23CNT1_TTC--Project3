package k23cnt1.nnh.project3.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nnh_danh_muc") // Sửa tên bảng thành chữ thường gạch dưới
public class NnhDanhMuc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nnh_id") // Map với cột nnh_id
    private Integer nnhId;

    @Column(name = "nnh_ten_danh_muc") // Map với cột nnh_ten_danh_muc
    private String nnhTenDanhMuc;

    @Column(name = "nnh_mo_ta") // Thêm cột mô tả (nếu có trong DB)
    private String nnhMoTa;

    @Column(name = "nnh_status") // Thêm cột trạng thái (nếu có trong DB)
    private Boolean nnhStatus;

    // ================= GETTER & SETTER =================
    public Integer getNnhId() { return nnhId; }
    public void setNnhId(Integer nnhId) { this.nnhId = nnhId; }

    public String getNnhTenDanhMuc() { return nnhTenDanhMuc; }
    public void setNnhTenDanhMuc(String nnhTenDanhMuc) { this.nnhTenDanhMuc = nnhTenDanhMuc; }

    public String getNnhMoTa() { return nnhMoTa; }
    public void setNnhMoTa(String nnhMoTa) { this.nnhMoTa = nnhMoTa; }

    public Boolean getNnhStatus() { return nnhStatus; }
    public void setNnhStatus(Boolean nnhStatus) { this.nnhStatus = nnhStatus; }
}