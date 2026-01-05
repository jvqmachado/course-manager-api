package br.com.joaovitor.coursehub.repository;

import br.com.joaovitor.coursehub.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    public Student findByEmail(String email);
}
