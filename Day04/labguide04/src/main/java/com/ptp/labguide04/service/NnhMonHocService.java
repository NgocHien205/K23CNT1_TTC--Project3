package com.ptp.labguide04.service;

import com.ptp.labguide04.dto.NnhMonHocDTO;
import com.ptp.labguide04.entity.NnhMonHoc;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class NnhMonHocService {
    private final List<NnhMonHoc> monhocs = new ArrayList<>(List.of(
            new NnhMonHoc("JAVA", "Lập trình Java", 60),
            new NnhMonHoc("WEB", "Thiết kế Web", 45),
            new NnhMonHoc("DB", "Cơ sở dữ liệu", 50),
            new NnhMonHoc("AI", "Trí tuệ nhân tạo", 70),
            new NnhMonHoc("MOB", "Lập trình Mobile", 65)
    ));

    public List<NnhMonHoc> getAll() { return monhocs; }
    public NnhMonHoc getByMa(String nnhmamh) { return monhocs.stream().filter(m -> m.nnhmamh().equals(nnhmamh)).findFirst().orElse(null); }
    public NnhMonHoc add(NnhMonHocDTO dto) { NnhMonHoc mh = new NnhMonHoc(dto.nnhmamh(), dto.nnhtenmh(), dto.nnhsotiet()); monhocs.add(mh); return mh; }
    public NnhMonHoc update(String nnhmamh, NnhMonHocDTO dto) {
        NnhMonHoc mh = getByMa(nnhmamh);
        if (mh == null) return null;
        monhocs.removeIf(item -> item.nnhmamh().equals(nnhmamh));
        NnhMonHoc updated = new NnhMonHoc(dto.nnhmamh(), dto.nnhtenmh(), dto.nnhsotiet());
        monhocs.add(updated);
        return updated;
    }
    public boolean delete(String mamh) { return monhocs.removeIf(m -> m.nnhmamh().equals(mamh)); }
}