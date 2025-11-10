package com.ptp.labguide04.service;

import com.ptp.labguide04.dto.NnhKhoaDTO;
import com.ptp.labguide04.entity.NnhKhoa;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class NnhKhoaService {
    private final List<NnhKhoa> nnhKhoas = new ArrayList<>(List.of(
            new NnhKhoa("CNTT", "Công nghệ thông tin"),
            new NnhKhoa("KT", "Kế toán"),
            new NnhKhoa("QTKD", "Quản trị kinh doanh"),
            new NnhKhoa("NN", "Ngoại ngữ"),
            new NnhKhoa("SP", "Sư phạm")
    ));

    public List<NnhKhoa> getAll() { return nnhKhoas; }
    public NnhKhoa getByMa(String nnhmakh) { return nnhKhoas.stream().filter(k -> k.nnhmakh().equals(nnhmakh)).findFirst().orElse(null); }
    public NnhKhoa add(NnhKhoaDTO dto) {
        NnhKhoa k = new NnhKhoa(dto.nnhmakh(), dto.nnhtenkh());
        nnhKhoas.add(k);
        return k;
    }
    public NnhKhoa update(String nnhmakh, NnhKhoaDTO dto) {
        NnhKhoa k = getByMa(nnhmakh);
        if (k == null) return null;
        nnhKhoas.removeIf(item -> item.nnhmakh().equals(nnhmakh));
        NnhKhoa updated = new NnhKhoa(dto.nnhmakh(), dto.nnhtenkh());
        nnhKhoas.add(updated);
        return updated;
    }
    public boolean delete(String nnhmakh) { return nnhKhoas.removeIf(k -> k.nnhmakh().equals(nnhmakh)); }
}