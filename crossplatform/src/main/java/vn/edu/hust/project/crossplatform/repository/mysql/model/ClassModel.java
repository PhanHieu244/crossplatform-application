package vn.edu.hust.project.crossplatform.repository.mysql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import vn.edu.hust.project.crossplatform.constant.ClassType;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "classes")
public class ClassModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "class_name", nullable = false, length = 100)
    private String className;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "lecturer_id")
    private Integer lecturerId;

    @Column(name = "schedule")
    private String schedule;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "max_student_amount", nullable = false)
    private Integer maxStudentAmount;

    @Column(name = "code", nullable = false, length = 6)
    private String code;

    @Column(name = "attached_code", length = 6)
    private String attachedCode;

    @Column(name = "course_code", nullable = false, length = 10)
    private String courseCode;

    @ColumnDefault("'LT'")
    @Enumerated(EnumType.STRING)
    @Column(name = "class_type", nullable = false)
    private ClassType classType;

    @Size(max = 20)
    @Column(name = "status", length = 20)
    private String status;

}