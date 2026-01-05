package br.com.joaovitor.coursehub.controller;

import br.com.joaovitor.coursehub.model.Student;
import br.com.joaovitor.coursehub.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addNew(@RequestBody Student student) {
        return ResponseEntity.status(201).body(studentService.create(student));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.status(200).body(studentService.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(studentService.readById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> update(@PathVariable Integer id, @RequestBody Student student) {
        Student update = studentService.update(id, student);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.deactivate(id));
    }
}
