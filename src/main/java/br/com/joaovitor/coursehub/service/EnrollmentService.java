package br.com.joaovitor.coursehub.service;

import br.com.joaovitor.coursehub.exceptions.ConflictException;
import br.com.joaovitor.coursehub.exceptions.NotFoundException;
import br.com.joaovitor.coursehub.model.Course;
import br.com.joaovitor.coursehub.model.Enrollment;
import br.com.joaovitor.coursehub.model.EnrollmentStatus;
import br.com.joaovitor.coursehub.model.Student;
import br.com.joaovitor.coursehub.repository.CourseRepository;
import br.com.joaovitor.coursehub.repository.EnrollmentRepository;
import br.com.joaovitor.coursehub.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Enrollment enroll(Integer studentId, Integer courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found"));

        if (!student.getActive()) {
            throw new ConflictException("Student is not active!");
        }
        if (!course.getActive()) {
            throw new ConflictException("Course is not active!");
        }
        boolean alreadyEnrolled = enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
        if (alreadyEnrolled) {
            throw new ConflictException("Student already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());
        enrollment.setStatus(EnrollmentStatus.ENROLLED);

        return enrollmentRepository.save(enrollment);
    }

    public Enrollment deactivate(Integer enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new NotFoundException("Enrollment not found!"));
        if (enrollment.getStatus() != EnrollmentStatus.ENROLLED) {
            throw new ConflictException("Enrollment must be ENROLLED to perform this action. Current status: "
                    + enrollment.getStatus());
        }
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment complete(Integer enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new NotFoundException("Enrollment not found"));
        if (enrollment.getStatus() != EnrollmentStatus.ENROLLED) {
            throw new ConflictException("Only ENROLLMENT enrollments can be completed");
        }

        enrollment.setStatus(EnrollmentStatus.COMPLETED);
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> readAll() {
        return enrollmentRepository.findAll();
    }

    public Enrollment readById(Integer id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Enrollment not found"));
        return enrollment;
    }

    public List<Enrollment> readByStudent(String email) {
        return enrollmentRepository.findByStudentEmail(email);
    }

    public List<Enrollment> readByCourse(String title) {
        return enrollmentRepository.findByCourseTitle(title);
    }

    public List<Enrollment> readByStatus(String status) {
        EnrollmentStatus enrollmentStatus;
        try {
            enrollmentStatus = EnrollmentStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Invalid enrollment status: " + status);
        }
        return enrollmentRepository.findByStatus(enrollmentStatus);
    }
}
