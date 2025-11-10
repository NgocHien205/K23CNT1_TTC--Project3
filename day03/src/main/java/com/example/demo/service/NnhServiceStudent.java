package com.example.demo.service;
import com.example.demo.entity.NnhStudent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NnhServiceStudent {

    private List<NnhStudent> students = new ArrayList<>();

    public NnhServiceStudent() {
        students.addAll(Arrays.asList(
                new NnhStudent(1L, "Devmaster 1", 20, "Nam", "SĐT", "0978611889", "ngochien@gmail.com"),
                new NnhStudent(2L, "Devmaster 2", 25, "Nam", "SĐT", "0978611889", "contact@devmaster.edu.vn"),
                new NnhStudent(3L, "Devmaster 3", 22, "Nam", "SĐT", "0978611889", "chungtrinh@gmail.com")
        ));
    }

    public List<NnhStudent> getStudents() {
        return students;
    }

    public NnhStudent getStudent(Long id) {
        return students.stream()
                .filter(student -> student.getNnhId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public NnhStudent addStudent(NnhStudent student) {
        students.add(student);
        return student;
    }

    public NnhStudent updateStudent(Long id, NnhStudent student) {
        NnhStudent check = getStudent(id);
        if (check == null) {
            return null;
        }

        students.forEach(item -> {
            if (item.getNnhId().equals(id)) {
                item.setNnhEmail(student.getNnhName());
                item.setNnhGender(student.getNnhGender());
            }
        });

        return student;
    }

    public boolean deleteStudent(Long id) {
        NnhStudent student = getStudent(id);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }
}