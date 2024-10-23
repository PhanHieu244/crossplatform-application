package vn.edu.hust.project.crossplatform.mapper;

import vn.edu.hust.project.crossplatform.dto.MessageDto;
import vn.edu.hust.project.crossplatform.dto.request.SendMessageRequest;
import vn.edu.hust.project.crossplatform.dto.response.LastMessage;
import vn.edu.hust.project.crossplatform.dto.response.MessageResponse;
import vn.edu.hust.project.crossplatform.repository.mysql.mapper.AccountMapper;
import vn.edu.hust.project.crossplatform.service.IAuthService;

public class MessageMapper {
    public static final MessageMapper INSTANCE = new MessageMapper();

    private MessageMapper() {}

    public MessageDto requestToDto(SendMessageRequest request, IAuthService authService) {
        if(request == null) return null;
        var sender = authService.getAccountByToken(request.getToken());
        var receiver = authService.getAccountById(request.getReceiver().getId());
        return MessageDto.builder()
                .sender(AccountMapper.INSTANCE.accountToUserDto(sender))
                .receiver(AccountMapper.INSTANCE.accountToUserDto(receiver))
                .conversationId(request.getConversationId())
                .content(request.getContent())
                .build();
    }

    public LastMessage toLastMessage(MessageDto messageDto) {
        if(messageDto == null) return null;
        return LastMessage.builder()
                .createdAt(messageDto.getCreatedAt())
                .message(messageDto.getContent())
                .sender(messageDto.getSender())
                .unread(messageDto.getMessageStatus())
                .build();

    }

    public MessageResponse toMessageResponse(MessageDto messageDto) {
        if(messageDto == null) return null;
        return MessageResponse.builder()
                .messageId(messageDto.getId())
                .createdAt(messageDto.getCreatedAt())
                .message(messageDto.getContent())
                .sender(messageDto.getSender())
                .unread(messageDto.getMessageStatus())
                .build();
    }
}
