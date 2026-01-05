package br.com.joaovitor.coursehub.controller;

import br.com.joaovitor.coursehub.model.Course;
import br.com.joaovitor.coursehub.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> addNew(@RequestBody Course course) {
        return ResponseEntity.status(201).body(courseService.create(course));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        return ResponseEntity.status(200).body(courseService.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(courseService.readById(id));
    }

    @GetMapping("/active")
    public ResponseEntity<List<Course>> getByActive() {
        return ResponseEntity.status(200).body(courseService.readByActive());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Integer id, Course course) {
        return ResponseEntity.ok(courseService.update(id, course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(courseService.deactivate(id));
    }

}
