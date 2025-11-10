package com.example.demo.controller;

import com.example.demo.entity.NnhStudent;
import com.example.demo.service.NnhServiceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class NnhStudentController {

    @Autowired
    private NnhServiceStudent studentService;

    @GetMapping("/student-list")
    public List<NnhStudent> getAllStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/student/{id}")
    public NnhStudent getStudentById(@PathVariable String id) {
        Long param = Long.parseLong(id);
        return studentService.getStudent(param);
    }

    @PostMapping("/student-add")
    public NnhStudent addStudent(@RequestBody NnhStudent student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/student/{id}")
    public NnhStudent updateStudent(@PathVariable String id, @RequestBody NnhStudent student) {
        Long param = Long.parseLong(id);
        return studentService.updateStudent(param, student);
    }

    @DeleteMapping("/student/{id}")
    public boolean deleteStudent(@PathVariable String id) {
        Long param = Long.parseLong(id);
        return studentService.deleteStudent(param);
    }
}