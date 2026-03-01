package com.example.student.controller;

import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentApiController {

    @Autowired

    private final StudentService service;

    public StudentApiController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student saved = service.save(student);
        return ResponseEntity.created(URI.create("/api/students/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable String id, @RequestBody Student student) {
        return service.getById(id).map(existing -> {
            existing.setName(student.getName());
            existing.setEmail(student.getEmail());
            existing.setAge(student.getAge());
            Student updated = service.save(existing);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/test")
    public List<Student> test() {
        return studentRepository.findAll();
    }
}
