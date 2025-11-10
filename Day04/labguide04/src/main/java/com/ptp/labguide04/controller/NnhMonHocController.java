package com.ptp.labguide04.controller;

import com.ptp.labguide04.dto.NnhMonHocDTO;
import com.ptp.labguide04.entity.NnhMonHoc;
import com.ptp.labguide04.service.NnhMonHocService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/monhoc")
public class NnhMonHocController {
    @Autowired private NnhMonHocService service;
    @GetMapping("") public List<NnhMonHoc> getAll() { return service.getAll(); }
    @GetMapping("/{mamh}") public NnhMonHoc getByMa(@PathVariable String nnhmamh) { return service.getByMa(nnhmamh); }
    @PostMapping("") public NnhMonHoc add(@Valid @RequestBody NnhMonHocDTO dto) { return service.add(dto); }
    @PutMapping("/{mamh}") public NnhMonHoc update(@PathVariable String nnhmamh, @Valid @RequestBody NnhMonHocDTO dto) { return service.update(nnhmamh, dto); }
    @DeleteMapping("/{mamh}") public boolean delete(@PathVariable String nnhmamh) { return service.delete(nnhmamh); }
}