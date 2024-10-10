package vn.edu.hust.project.crossplatform.repository.mysql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "assignment_submissions")
public class AssignmentSubmissionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "assignment_id")
    private Integer assignmentId;

    @Column(name = "student_id")
    private Integer studentId;

    @CreationTimestamp
    @Column(name = "submission_time", nullable = false, updatable = false)
    private LocalDateTime submissionTime;

    @Column(name = "grade")
    private Float grade;

    @Size(max = 300)
    @Column(name = "file_url", length = 300)
    private String fileUrl;

    @Lob
    @Column(name = "text_response")
    private String textResponse;

}