package br.com.joaovitor.coursehub.repository;

import br.com.joaovitor.coursehub.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    public Course findByTitle(String title);
    public List<Course> findByActiveTrue();
}
