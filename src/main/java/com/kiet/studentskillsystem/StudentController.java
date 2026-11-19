package com.kiet.studentskillsystem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // 1. Add a student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addStudent(student));
    }

    // 2. Retrieve all students
    @GetMapping
    public List<Student> getAll() {
        return service.getAllStudents();
    }

    // 6. Merit list
    @GetMapping("/merit-list")
    public List<Student> meritList() {
        return service.meritList();
    }

    // 3. Search by ID
    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        return service.getStudentById(id);
    }

    // 4. Update scores and skills
    @PutMapping("/{id}")
    public Student update(@PathVariable int id, @RequestBody UpdateRequest request) {
        return service.updateScoresAndSkills(id, request);
    }

    // 5. Delete a student
    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable int id) {
        service.deleteStudent(id);
        return Map.of("message", "Student " + id + " deleted");
    }
}