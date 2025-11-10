package com.ptp.labguide04.controller;

import com.ptp.labguide04.dto.NnhKhoaDTO;
import com.ptp.labguide04.entity.NnhKhoa;
import com.ptp.labguide04.service.NnhKhoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/khoa")
public class NnhKhoaController {
    @Autowired private NnhKhoaService service;
    @GetMapping("") public List<NnhKhoa> getAll() { return service.getAll(); }
    @GetMapping("/{makh}") public NnhKhoa getByMa(@PathVariable String nnhmakh) { return service.getByMa(nnhmakh); }
    @PostMapping("") public NnhKhoa add(@Valid @RequestBody NnhKhoaDTO dto) { return service.add(dto); }
    @PutMapping("/{makh}") public NnhKhoa update(@PathVariable String nnhmakh, @Valid @RequestBody NnhKhoaDTO dto) { return service.update(nnhmakh, dto); }
    @DeleteMapping("/{makh}") public boolean delete(@PathVariable String nnhmakh) { return service.delete(nnhmakh); }
}