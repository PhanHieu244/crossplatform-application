package vn.edu.hust.project.crossplatform.repository.mysql;

import vn.edu.hust.project.crossplatform.dto.projection.ConversationProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Conversation;

import java.util.List;
import java.util.Optional;

@Repository
public interface IConversationRepository extends IBaseRepository<Conversation> {

    @Query(value = "SELECT c.id, " +
            "(SELECT GROUP_CONCAT(cp.partner_id) " +
            " FROM conversation_partners cp " +
            " WHERE cp.conversation_id = c.id AND cp.partner_id <> :partnerId) AS partnerIds, " +
            "c.current_message_id " +
            "FROM conversations c " +
            "JOIN conversation_partners cp ON cp.conversation_id = c.id " +
            "WHERE cp.partner_id = :partnerId " +
            "ORDER BY c.updated_at DESC " +
            "LIMIT :count OFFSET :offset", nativeQuery = true)
    List<Object[]> findRecentConversationsByPartner(
            @Param("partnerId") Integer partnerId,
            @Param("count") Integer count,
            @Param("offset") Integer offset
    );


    @Query(value = "SELECT c.* FROM conversations c " +
            "JOIN conversation_partners cp ON cp.conversation_id = c.id " +
            "WHERE cp.partner_id IN (:senderId, :receiverId) " +
            "GROUP BY c.id " +
            "HAVING COUNT(DISTINCT cp.partner_id) = 2", nativeQuery = true)
    Optional<Conversation> findInboxConversation(
            @Param("senderId") Integer senderId,
            @Param("receiverId") Integer receiverId);
}
