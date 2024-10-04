package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Attendance;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IAttendanceRepository extends IBaseRepository<Attendance> {
    @Query("SELECT a FROM Attendance a WHERE a.attendanceTime = :date AND a.classDetailId IN :classDetailIds")
    List<Attendance> getAllAtAttendanceTimeAndClassDetailIds(LocalDate date, List<Integer> classDetailIds);
    List<Attendance> getAttendancesByClassDetailId(Integer classDetailId);
}
