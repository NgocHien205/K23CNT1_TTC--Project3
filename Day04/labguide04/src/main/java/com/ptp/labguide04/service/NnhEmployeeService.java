package com.ptp.labguide04.service;

import com.ptp.labguide04.dto.NnhEmployeeDTO;
import com.ptp.labguide04.entity.NnhEmployee;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class NnhEmployeeService {
    private final List<NnhEmployee> nnhEmployees = new ArrayList<>(List.of(
            new NnhEmployee(1L, "Nguyễn Ngọc Hiến", "Nam", 20, 15000000),
            new NnhEmployee(2L, "Trần Thị B", "Nữ", 30, 20000000),
            new NnhEmployee(3L, "Lê Văn C", "Nam", 28, 18000000)
    ));

    public List<NnhEmployee> getAll() { return nnhEmployees; }
    public NnhEmployee getById(Long id) { return nnhEmployees.stream().filter(e -> e.nnhid().equals(id)).findFirst().orElse(null); }

    public NnhEmployee add(NnhEmployeeDTO dto) {
        Long newId = nnhEmployees.stream().mapToLong(NnhEmployee::nnhid).max().orElse(0L) + 1;
        NnhEmployee emp = new NnhEmployee(newId, dto.nnhfullName(), dto.nnhgender(), dto.nnhage(), dto.nnhsalary());
        nnhEmployees.add(emp);
        return emp;
    }

    public NnhEmployee update(Long id, NnhEmployeeDTO dto) {
        NnhEmployee e = getById(id);
        if (e == null) return null;
        nnhEmployees.removeIf(item -> item.nnhid().equals(id));
        NnhEmployee updated = new NnhEmployee(id, dto.nnhfullName(), dto.nnhgender(), dto.nnhage(), dto.nnhsalary());
        nnhEmployees.add(updated);
        return updated;
    }

    public boolean delete(Long id) { return nnhEmployees.removeIf(e -> e.nnhid().equals(id)); }
}