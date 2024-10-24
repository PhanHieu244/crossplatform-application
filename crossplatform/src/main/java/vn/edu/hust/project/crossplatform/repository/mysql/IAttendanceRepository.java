package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.AttendanceModel;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IAttendanceRepository extends IBaseRepository<AttendanceModel> {
    @Query("SELECT a FROM AttendanceModel a WHERE a.attendanceTime = :date AND a.classDetailId IN :classDetailIds")
    List<AttendanceModel> getAllAtAttendanceTimeAndClassDetailIds(LocalDate date, List<Integer> classDetailIds);
    List<AttendanceModel> getAttendancesByClassDetailId(Integer classDetailId);
}
