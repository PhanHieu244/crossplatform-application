package vn.edu.hust.project.crossplatform.service;

import vn.edu.hust.project.crossplatform.dto.MessageDto;
import vn.edu.hust.project.crossplatform.dto.request.DeleteMessageRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetConversationRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetListConversationRequest;
import vn.edu.hust.project.crossplatform.dto.request.SendMessageRequest;
import vn.edu.hust.project.crossplatform.dto.response.GetConversationResponse;
import vn.edu.hust.project.crossplatform.dto.response.ListConversationResponse;

public interface IChatService {
    void sendMessage(SendMessageRequest request);

    ListConversationResponse getListConversation(GetListConversationRequest request);

    GetConversationResponse getConversation(GetConversationRequest request);

    MessageDto deleteMessage(DeleteMessageRequest request);
}
