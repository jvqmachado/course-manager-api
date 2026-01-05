package br.com.joaovitor.coursehub.service;

import br.com.joaovitor.coursehub.exceptions.ConflictException;
import br.com.joaovitor.coursehub.exceptions.NotFoundException;
import br.com.joaovitor.coursehub.model.Student;
import br.com.joaovitor.coursehub.repository.EnrollmentRepository;
import br.com.joaovitor.coursehub.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentService(StudentRepository studentRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Student create(Student student) {
        Student res = studentRepository.findByEmail(student.getEmail());
        if (res != null) {
            throw new ConflictException("Student already exists!");
        }
        student.setCreatedAt(LocalDateTime.now());
        return studentRepository.save(student);
    }

    public List<Student> readAll() {
        return studentRepository.findAll();
    }

    public Student readById(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found!"));
    }

    public Student update(Integer id, Student update) {
        Student existing = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found!"));
        Student byEmail = studentRepository.findByEmail(update.getEmail());
        if (byEmail != null && !byEmail.getId().equals(id)) {
            throw new ConflictException("Email already in use");
        }
        existing.setName(update.getName());
        existing.setEmail(update.getEmail());
        return studentRepository.save(existing);
    }

    public Student deactivate(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found!"));

        boolean hasEnrrolments = enrollmentRepository.existsByStudentId(id);
        if (hasEnrrolments) {
            throw new ConflictException("Student has enrollments and cannot be deactivated");
        }

        student.setActive(false);
        return studentRepository.save(student);
    }
}
