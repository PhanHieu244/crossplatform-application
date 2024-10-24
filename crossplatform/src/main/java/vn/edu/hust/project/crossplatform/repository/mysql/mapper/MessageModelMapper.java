package vn.edu.hust.project.crossplatform.repository.mysql.mapper;

import vn.edu.hust.project.crossplatform.dto.MessageDto;
import vn.edu.hust.project.crossplatform.repository.mysql.model.MessageModel;
import vn.edu.hust.project.crossplatform.service.IAuthService;

public class MessageModelMapper {
    public static final MessageModelMapper INSTANCE = new MessageModelMapper();
    private MessageModelMapper() { }

    public MessageModel toModel(MessageDto messageDto) {
        var model = new MessageModel();
        model.setSenderId(messageDto.getSender().getId());
        model.setStatus(messageDto.getMessageStatus());
        model.setContent(messageDto.getContent());
        model.setConversationId(messageDto.getConversationId());
        return model;
    }

    public MessageDto toDto(MessageModel model, IAuthService authService) {
        var sender = AccountMapper.INSTANCE.accountToUserDto(authService.getAccountById(model.getSenderId()));
        return MessageDto.builder()
                .id(model.getId())
                .sender(sender)
                .messageStatus(model.getStatus())
                .createdAt(model.getCreatedAt())
                .content(model.getContent())
                .conversationId(model.getConversationId())
                .build();
    }

}
