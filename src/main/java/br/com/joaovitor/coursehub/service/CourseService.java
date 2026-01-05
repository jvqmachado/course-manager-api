package br.com.joaovitor.coursehub.service;

import br.com.joaovitor.coursehub.exceptions.ConflictException;
import br.com.joaovitor.coursehub.exceptions.NotFoundException;
import br.com.joaovitor.coursehub.model.Course;
import br.com.joaovitor.coursehub.repository.CourseRepository;
import br.com.joaovitor.coursehub.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseService(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Course create(Course newCourse) {
        Course res = courseRepository.findByTitle(newCourse.getTitle());
        if (res != null) {
            throw new ConflictException("Course already exists!");
        }
        newCourse.setActive(true);
        return courseRepository.save(newCourse);
    }

    public Course readById(Integer id) {
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found!"));
    }

    public List<Course> readAll() {
        return courseRepository.findAll();
    }

    public List<Course> readByActive() {
        return courseRepository.findByActiveTrue();
    }

    public Course update(Integer id, Course update) {
        Course existing = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found!"));
        existing.setTitle(update.getTitle());
        existing.setDescription(update.getDescription());
        existing.setWorkloadHours(update.getWorkloadHours());
        existing.setActive(update.getActive());

        return courseRepository.save(existing);
    }

    public Course deactivate(Integer courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found!"));

        boolean hasEnrrolments = enrollmentRepository.existsByCourseId(courseId);

        if (hasEnrrolments) {
            throw new ConflictException("Course has enrroled students and cannot be deactivated!");
        }

        course.setActive(false);
        return courseRepository.save(course);
    }
}
