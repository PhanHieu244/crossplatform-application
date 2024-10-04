package vn.edu.hust.project.crossplatform.repository.mysql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vn.edu.hust.project.crossplatform.constant.AttendanceStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "attendance_status", nullable = false, length = 25)
    private AttendanceStatus attendanceStatus;

    @Column(name = "attendance_time")
    @NotNull
    private LocalDate attendanceTime;

    @Column(name = "class_detail_id")
    @NotNull
    private Integer classDetailId;

}