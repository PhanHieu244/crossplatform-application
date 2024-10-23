package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.MessageModel;

import java.util.List;

@Repository
public interface IMessageRepository extends IBaseRepository<MessageModel> {
    @Query(value = "SELECT * FROM messages m " +
            "WHERE m.conversation_id = :conversationId " +
            "ORDER BY m.created_at DESC " +
            "LIMIT :count OFFSET :offset", nativeQuery = true)
    List<MessageModel> findMessagesByConversationId(
            @Param("conversationId") Integer conversationId,
            @Param("count") Integer count,
            @Param("offset") Integer offset
    );

}
