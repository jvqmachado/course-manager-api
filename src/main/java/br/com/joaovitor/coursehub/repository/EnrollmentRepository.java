package br.com.joaovitor.coursehub.repository;

import br.com.joaovitor.coursehub.model.Enrollment;
import br.com.joaovitor.coursehub.model.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    public Boolean existsByCourseId(Integer courseId);
    public Boolean existsByStudentId(Integer studentId);
    public Boolean existsByStudentIdAndCourseId(Integer studentId, Integer CourseId);
    public List<Enrollment> findByStudentEmail(String email);
    public List<Enrollment> findByCourseTitle(String title);
    public List<Enrollment> findByStatus(EnrollmentStatus status);
}
