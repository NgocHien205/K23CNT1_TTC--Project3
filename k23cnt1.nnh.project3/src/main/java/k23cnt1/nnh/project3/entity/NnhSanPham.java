package k23cnt1.nnh.project3.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nnh_san_pham") // 1. Sửa tên bảng
public class NnhSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nnh_id") // Map cột ID
    private Integer nnhId;

    @Column(name = "nnh_ten_san_pham") // Map cột tên
    private String nnhTenSanPham;

    @Column(name = "nnh_gia") // Map cột giá
    private Float nnhGia;

    @Column(name = "nnh_so_luong") // Map cột số lượng
    private Integer nnhSoLuong;

    @Column(name = "nnh_hinh_anh") // Map cột hình ảnh
    private String nnhHinhAnh;

    @Column(name = "nnh_mo_ta") // Map cột mô tả
    private String nnhMoTa;

    // Mapping tới bảng Danh Mục (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "nnh_danh_muc_id") // 2. QUAN TRỌNG: Sửa tên khóa ngoại cho khớp SQL
    private NnhDanhMuc nnhDanhMuc;

    // ================= GETTER & SETTER =================
    public Integer getNnhId() { return nnhId; }
    public void setNnhId(Integer nnhId) { this.nnhId = nnhId; }

    public String getNnhTenSanPham() { return nnhTenSanPham; }
    public void setNnhTenSanPham(String nnhTenSanPham) { this.nnhTenSanPham = nnhTenSanPham; }

    public Float getNnhGia() { return nnhGia; }
    public void setNnhGia(Float nnhGia) { this.nnhGia = nnhGia; }

    public Integer getNnhSoLuong() { return nnhSoLuong; }
    public void setNnhSoLuong(Integer nnhSoLuong) { this.nnhSoLuong = nnhSoLuong; }

    public String getNnhHinhAnh() { return nnhHinhAnh; }
    public void setNnhHinhAnh(String nnhHinhAnh) { this.nnhHinhAnh = nnhHinhAnh; }

    public String getNnhMoTa() { return nnhMoTa; }
    public void setNnhMoTa(String nnhMoTa) { this.nnhMoTa = nnhMoTa; }

    public NnhDanhMuc getNnhDanhMuc() { return nnhDanhMuc; }
    public void setNnhDanhMuc(NnhDanhMuc nnhDanhMuc) { this.nnhDanhMuc = nnhDanhMuc; }
}