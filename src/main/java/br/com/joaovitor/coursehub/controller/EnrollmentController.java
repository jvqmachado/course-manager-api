package br.com.joaovitor.coursehub.controller;

import br.com.joaovitor.coursehub.model.Enrollment;
import br.com.joaovitor.coursehub.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<Enrollment> addNew(@RequestParam Integer studentId, @RequestParam Integer courseId) {
        return ResponseEntity.status(201).body(enrollmentService.enroll(studentId, courseId));
    }

    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<Enrollment> cancel(@PathVariable Integer id) {
        return ResponseEntity.ok(enrollmentService.deactivate(id));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Enrollment> complete(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(enrollmentService.complete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(enrollmentService.readById(id));
    }

  @GetMapping
    public ResponseEntity<List<Enrollment>> getByFilters(@RequestParam(required = false) String email,
                                                         @RequestParam(required = false) String title,
                                                         @RequestParam(required = false) String status) {
        if (email != null) {
            return ResponseEntity.ok(enrollmentService.readByStudent(email));
        }
        if (title != null) {
            return ResponseEntity.ok(enrollmentService.readByCourse(title));
        }
        if (status != null) {
            return ResponseEntity.ok(enrollmentService.readByStatus(status));
        }
        return ResponseEntity.ok(enrollmentService.readAll());
  }
}
