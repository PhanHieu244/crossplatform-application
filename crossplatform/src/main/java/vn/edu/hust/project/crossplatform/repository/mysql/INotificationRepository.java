package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.constant.NotificationStatus;
import vn.edu.hust.project.crossplatform.repository.mysql.model.NotificationModel;

import java.util.List;

@Repository
public interface INotificationRepository extends IBaseRepository<NotificationModel> {
    Integer countByToUserAndStatus(Integer toUser, NotificationStatus status);

    @Query(value = "SELECT * FROM notifications n " +
            "WHERE n.to_user = :toUser " +
            "ORDER BY n.sent_time DESC " +
            "LIMIT :count OFFSET :offset ", nativeQuery = true)
    List<NotificationModel> findByToUser(
            @Param("toUser") Integer toUser,
            @Param("offset") Integer offset,
            @Param("count") Integer count
    );
}
