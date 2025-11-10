package com.ptp.labguide04.controller;

import com.ptp.labguide04.dto.NnhEmployeeDTO;
import com.ptp.labguide04.entity.NnhEmployee;
import com.ptp.labguide04.service.NnhEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class NnhEmployeeController {
    @Autowired private NnhEmployeeService service;
    @GetMapping("") public List<NnhEmployee> getAll() { return service.getAll(); }
    @GetMapping("/{id}") public NnhEmployee getById(@PathVariable Long nnhid) { return service.getById(nnhid); }
    @PostMapping("") public NnhEmployee add(@Valid @RequestBody NnhEmployeeDTO dto) { return service.add(dto); }
    @PutMapping("/{id}") public NnhEmployee update(@PathVariable Long nnhid, @Valid @RequestBody NnhEmployeeDTO dto) { return service.update(nnhid, dto); }
    @DeleteMapping("/{id}") public boolean delete(@PathVariable Long nnhid) { return service.delete(nnhid); }
}