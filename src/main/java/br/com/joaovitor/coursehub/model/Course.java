package br.com.joaovitor.coursehub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer id;

    @Column(name = "course_title", length = 255, nullable = false)
    private String title;

    @Column(name = "course_description", length = 300)
    private String description;

    @Column(name = "workload_hours", nullable = false)
    private Integer workloadHours;

    @Column(name = "course_active", nullable = false)
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWorkloadHours() {
        return workloadHours;
    }

    public void setWorkloadHours(Integer workloadHours) {
        this.workloadHours = workloadHours;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
