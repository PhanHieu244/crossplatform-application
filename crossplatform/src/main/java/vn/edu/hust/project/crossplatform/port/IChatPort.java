package vn.edu.hust.project.crossplatform.port;

import vn.edu.hust.project.crossplatform.dto.MessageDto;
import vn.edu.hust.project.crossplatform.dto.request.DeleteMessageRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetConversationRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetListConversationRequest;
import vn.edu.hust.project.crossplatform.dto.response.GetConversationResponse;
import vn.edu.hust.project.crossplatform.dto.response.ListConversationResponse;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Conversation;

import java.util.List;

public interface IChatPort {

    MessageDto saveMessage(MessageDto messageDto);

    ListConversationResponse getListConversation(GetListConversationRequest request);

    GetConversationResponse getConversation(GetConversationRequest request);

    MessageDto deleteMessage(DeleteMessageRequest request);

    Conversation createNewConversation(Integer... partnerIds);

    MessageDto getMessageById(Integer messageId);

    List<Integer> getAnotherPartners(Integer userId, Integer conversationId);

    void addToConversation(Integer conversationId, Integer... partnerIds);

}
