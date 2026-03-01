package com.example.student.controller;

import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentWebController {

    private final StudentService service;

    public StudentWebController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/students")
    public String list(Model model) {
        model.addAttribute("students", service.getAll());
        return "list";
    }

    @GetMapping("/students/{id}")
    public String detail(@PathVariable String id, Model model) {
        service.getById(id).ifPresent(s -> model.addAttribute("student", s));
        return "detail";
    }

    @GetMapping("/students/new")
    public String createForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    @PostMapping("/students")
    public String save(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        service.getById(id).ifPresent(s -> model.addAttribute("student", s));
        return "form";
    }
}
