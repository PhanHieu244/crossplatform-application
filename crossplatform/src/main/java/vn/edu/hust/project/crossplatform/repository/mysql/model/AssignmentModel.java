package vn.edu.hust.project.crossplatform.repository.mysql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "assignments")
public class AssignmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "lecturer_id")
    private Integer lecturerId;

    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Size(max = 300)
    @Column(name = "file_url", length = 300)
    private String fileUrl;

}