package vn.edu.hust.project.crossplatform.repository.mysql;

import org.springframework.stereotype.Repository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.ConversationPartner;

import java.util.List;

@Repository
public interface IConversationPartnerRepository extends IBaseRepository<ConversationPartner>{
    List<ConversationPartner> findByConversationId(Integer conversationId);
}
