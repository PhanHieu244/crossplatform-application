package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.constant.AbsenceRequestStatus;
import vn.edu.hust.project.crossplatform.repository.mysql.model.AbsenceRequestModel;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IAbsenceRequestRepository extends IBaseRepository<AbsenceRequestModel> {
    @Query("SELECT a FROM AbsenceRequestModel a " +
            "JOIN ClassDetailModel cd ON a.classDetailId = cd.id " +
            "WHERE cd.classId = :classId AND a.status = :status AND a.absenceDate = :date")
    List<AbsenceRequestModel> findAbsenceRequestsByClassAndStatusAndDate(
            @Param("classId") Integer classId,
            @Param("status") AbsenceRequestStatus status,
            @Param("date") LocalDate date);

    @Query("SELECT a FROM AbsenceRequestModel a " +
            "JOIN ClassDetailModel cd ON a.classDetailId = cd.id " +
            "WHERE cd.classId = :classId AND a.status = :status")
    List<AbsenceRequestModel> findAbsenceRequestsByClassAndStatus(
            @Param("classId") Integer classId,
            @Param("status") AbsenceRequestStatus status);
}
