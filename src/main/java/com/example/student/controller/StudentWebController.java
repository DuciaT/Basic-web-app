package com.example.student.controller;

import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class StudentWebController {

    private final StudentService service;

    public StudentWebController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/students";
    }

    // Trang danh sách - CHỈ ID VÀ TÊN
    @GetMapping("/students")
    public String list(Model model) {
        model.addAttribute("students", service.getAll());
        return "form";
    }

    // Trang chi tiết - ĐẦY ĐỦ THÔNG TIN + EDIT/DELETE
    @GetMapping("/students/{id}")
    public String detail(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Student> student = service.getById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "student-detail";
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sinh viên với ID: " + id);
            return "redirect:/students";
        }
    }

    // Form thêm mới
    @GetMapping("/students/new")
    public String createForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // Form chỉnh sửa
    @GetMapping("/students/edit/{id}")
    public String editForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Student> student = service.getById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "student-form";
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sinh viên với ID: " + id);
            return "redirect:/students";
        }
    }

    // Lưu sinh viên
    @PostMapping("/students/save")
    public String save(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        try {
            service.save(student);
            redirectAttributes.addFlashAttribute("message", "Lưu sinh viên thành công!");
            return "redirect:/students";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi lưu: " + e.getMessage());
            return "redirect:/students";
        }
    }

    // Xóa sinh viên
    @PostMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            Optional<Student> student = service.getById(id);
            if (student.isPresent()) {
                String name = student.get().getName();
                service.deleteById(id);
                redirectAttributes.addFlashAttribute("message", "Đã xóa sinh viên: " + name);
            } else {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy sinh viên!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi xóa: " + e.getMessage());
        }
        return "redirect:/students";
    }
}