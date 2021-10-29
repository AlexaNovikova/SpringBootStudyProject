package ru.geekbrains.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.models.Student;
import ru.geekbrains.spring.services.StudentService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // [http://localhost:8189/app]/students/all
    @GetMapping("/")
    public String showAllStudentsPage(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/{id}")
    public String showStudentInfo(@PathVariable(name = "id") Long id, Model model) {
        Optional<Student> student = studentService.findOneById(id);
        student.ifPresent(value -> model.addAttribute("student", value));
        return "student_info";
    }


    // [http://localhost:8189/app]/students/creator
    @GetMapping("/create")
    public String showCreator() {
        return "create_student_form";
    }

    // [http://localhost:8189/app]/students/create?id=10&name=Nicolas&score=95
//    @PostMapping("/create")
//    public String createNewStudent(@RequestParam Long id, @RequestParam String name, @RequestParam int score) {
//        Student student = new Student(id, name, score);
//        studentService.save(student);
//        return "redirect:/students/all";
//    }

        @PostMapping("/create")
       public String createNewStudent(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable Long id){
        studentService.deleteById(id);
        return "redirect:/";
  }
}

