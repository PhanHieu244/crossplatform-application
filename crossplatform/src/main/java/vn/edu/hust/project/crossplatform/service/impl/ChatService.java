package vn.edu.hust.project.crossplatform.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.dto.MessageDto;
import vn.edu.hust.project.crossplatform.dto.request.DeleteMessageRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetConversationRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetListConversationRequest;
import vn.edu.hust.project.crossplatform.dto.request.SendMessageRequest;
import vn.edu.hust.project.crossplatform.dto.response.DeleteMessageEvent;
import vn.edu.hust.project.crossplatform.dto.response.GetConversationResponse;
import vn.edu.hust.project.crossplatform.dto.response.ListConversationResponse;
import vn.edu.hust.project.crossplatform.mapper.MessageMapper;
import vn.edu.hust.project.crossplatform.port.IChatPort;
import vn.edu.hust.project.crossplatform.service.IAuthService;
import vn.edu.hust.project.crossplatform.service.IChatService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService implements IChatService {
    private final IAuthService authService;
    private final IChatPort chatPort;
    private final SimpMessagingTemplate messagingTemplate;

    public void sendMessage(SendMessageRequest request) {
        var messageDto = MessageMapper.INSTANCE.requestToDto(request, authService);

        var sendMessage = chatPort.saveMessage(messageDto);

        messagingTemplate.convertAndSendToUser(sendMessage.getSender().getId().toString(), "/inbox", sendMessage);
        if(sendMessage.getReceiver() == null) return;
        messagingTemplate.convertAndSendToUser(sendMessage.getReceiver().getId().toString(), "/inbox", sendMessage);
    }

    public ListConversationResponse getListConversation(GetListConversationRequest request){
        return chatPort.getListConversation(request);
    }

    public GetConversationResponse getConversation(GetConversationRequest request){
        return chatPort.getConversation(request);
    }

    public MessageDto deleteMessage(DeleteMessageRequest request){
        var user = authService.getAccountByToken(request.getToken());
        var deleteMessage = chatPort.deleteMessage(request);
        Integer partnerId = request.getPartnerId();
        if(partnerId == null){
            var anotherPartners = chatPort.getAnotherPartners(user.getId(), deleteMessage.getConversationId());
            if(anotherPartners.isEmpty()){
                return deleteMessage;
            }
            partnerId = anotherPartners.get(0);
        }
        var deleteEvent = DeleteMessageEvent.builder()
                .messageId(deleteMessage.getId())
                .conversationId(deleteMessage.getConversationId())
                .user(user.getId())
                .build();

        messagingTemplate.convertAndSendToUser(partnerId.toString(), "/inbox/delete", deleteEvent);
        return deleteMessage;
    }
}
