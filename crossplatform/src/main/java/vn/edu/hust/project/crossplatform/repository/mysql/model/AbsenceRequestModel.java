package vn.edu.hust.project.crossplatform.repository.mysql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import vn.edu.hust.project.crossplatform.constant.AbsenceRequestStatus;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "absence_request")
public class AbsenceRequestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "class_detail_id", nullable = false)
    private Integer classDetailId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 25)
    private AbsenceRequestStatus status;

    @NotNull
    @Column(name = "absence_date", nullable = false)
    private LocalDate absenceDate;

    @Lob
    @Column(name = "reason")
    private String reason;

}