package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.constant.MessageStatus;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.dto.MessageDto;
import vn.edu.hust.project.crossplatform.dto.UserDto;
import vn.edu.hust.project.crossplatform.dto.projection.ConversationProjection;
import vn.edu.hust.project.crossplatform.dto.request.DeleteMessageRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetConversationRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetListConversationRequest;
import vn.edu.hust.project.crossplatform.dto.response.GetConversationResponse;
import vn.edu.hust.project.crossplatform.dto.response.ListConversationResponse;
import vn.edu.hust.project.crossplatform.exception.NoDataException;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.mapper.MessageMapper;
import vn.edu.hust.project.crossplatform.port.IChatPort;
import vn.edu.hust.project.crossplatform.repository.mysql.IConversationPartnerRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.IConversationRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.IMessageRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.mapper.AccountMapper;
import vn.edu.hust.project.crossplatform.repository.mysql.mapper.MessageModelMapper;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Conversation;
import vn.edu.hust.project.crossplatform.repository.mysql.model.ConversationPartner;
import vn.edu.hust.project.crossplatform.repository.mysql.model.MessageModel;
import vn.edu.hust.project.crossplatform.service.IAuthService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatAdapter implements IChatPort {
    private final IConversationRepository conversationRepository;
    private final IMessageRepository messageRepository;
    private final IConversationPartnerRepository partnerRepository;
    private final IAuthService authService;

    public MessageDto saveMessage(MessageDto messageDto) {
        Integer conversationId = messageDto.getConversationId();
        List<ConversationPartner> anotherPartners = null;
        Integer receiverId = null;

        if (conversationId == null) {
            if(messageDto.getReceiver() == null){
                log.error("receiver and conversationId is null");
                throw new ApplicationException(
                        ResponseCode.PARAMETER_VALUE_IS_INVALID
                        ,"receiver or conversationId must be not null!!!"
                );
            }
            var senderId = messageDto.getSender().getId();
            receiverId = messageDto.getReceiver().getId();
            conversationId = getConversationByPartner(senderId, receiverId).getId();
        }
        else {
           anotherPartners = checkConversationHasPartner(conversationId, messageDto.getSender().getId());
        }

        //SAVE
        messageDto.setConversationId(conversationId);
        messageDto.setMessageStatus(MessageStatus.UNREAD);
        var sendMessage = MessageModelMapper.INSTANCE.toDto(
                messageRepository.save(MessageModelMapper.INSTANCE.toModel(messageDto)), authService
        );

        //GET RECEIVER IF DONT SEND RECEIVER
        if(anotherPartners != null && !anotherPartners.isEmpty()){
            var partner = anotherPartners.get(0);
            receiverId = partner.getId();
        }

        if(receiverId != null){
            var receiver = AccountMapper.INSTANCE.accountToUserDto(authService.getAccountById(receiverId));
            sendMessage.setReceiver(receiver);
        }

        //UPDATE LAST TO CONVERSATION
        saveLastMessageToConversation(sendMessage);

        return sendMessage;
    }

    private void saveLastMessageToConversation(MessageDto sendMessage){
        var conversation = conversationRepository.findById(sendMessage.getConversationId()).orElseThrow(
                () -> new ApplicationException("ERROR CONVERSATION")
        );
        conversation.setCurrentMessageId(sendMessage.getId());
        conversationRepository.save(conversation);
    }

    public ListConversationResponse getListConversation(GetListConversationRequest request) {
        var sender = authService.getAccountByToken(request.getToken());
        var conversationProjections = conversationRepository.findRecentConversationsByPartner(
                sender.getId(), request.getCount(), request.getIndex()
        ).stream().map(result -> new ConversationProjection(
                (Integer) result[0], // c.id
                (String) result[1],  // partnerIds
                (Integer) result[2]   // currentMessageId
        )).toList();

        var conversations = conversationProjections.stream()
                .map( projection ->
                        {
                            var messageId = projection.getMessageId();
                            var message = messageId!= null ? getMessageById(messageId) : null;
                            return projection.toConversationListItem(message, authService);
                        }
                )
                .toList();
        long numNewMessages = conversations.stream()
                .filter(c -> c.getLastMessage() != null
                        && c.getLastMessage().getUnread() == MessageStatus.UNREAD
                        && !Objects.equals(c.getLastMessage().getSender().getId(), sender.getId())
                )
                .count();
        return ListConversationResponse.builder()
                .conversations(conversations)
                .numNewMessage((int) numNewMessages)
                .build();
    }

    public GetConversationResponse getConversation(GetConversationRequest request){
        Integer conversationId = request.getConversationId();
        if(conversationId == null){
            var senderId = authService.getAccountByToken(request.getToken()).getId();
            conversationId = getConversationByPartner(senderId, request.getPartnerId()).getId();
        }
        else {
            if(!conversationRepository.existsById(request.getConversationId())){
                log.error("conversation with id {} not found", request.getConversationId());
                throw new NoDataException("conversation with id " + request.getConversationId() + " not found");
            }
        }

        var messages = messageRepository.findMessagesByConversationId(
                conversationId, request.getCount(), request.getIndex()
        );

        var messageResponses = messages.stream()
                .map(model -> MessageModelMapper.INSTANCE.toDto(model, authService))
                .map(MessageMapper.INSTANCE::toMessageResponse)
                .toList();

        if(request.getMarkAsRead() != null && request.getMarkAsRead()){
            messages.forEach(messageModel -> messageModel.setStatus(MessageStatus.READ));
            messageRepository.saveAll(messages);
        }

        return GetConversationResponse.builder()
                .conversation(messageResponses)
                .isBlocked(false)
                .build();
    }

    public MessageDto deleteMessage(DeleteMessageRequest request) {
        var messageModel = getMessageModelById(request.getMessageId());
        var accessUserId = authService.getAccountByToken(request.getToken()).getId();

        if(!Objects.equals(messageModel.getSenderId(), accessUserId)){
            log.error("can't delete message from another");
            throw new ApplicationException(
                    ResponseCode.PARAMETER_VALUE_IS_INVALID,
                    "cant delete message from another user"
            );
        }

        var conservationId = request.getConversationId();
        if(conservationId == null){
            conservationId = getConversationByPartner(request.getPartnerId(), accessUserId).getId();
        }

        if(!messageModel.getConversationId().equals(conservationId)){
            throw new ApplicationException(ResponseCode.PARAMETER_VALUE_IS_INVALID, "conversationId does not match");
        }

        var conversation = getConversationById(conservationId);
        if(Objects.equals(conversation.getCurrentMessageId(), request.getMessageId())){
            conversation.setCurrentMessageId(null);
            conversationRepository.save(conversation);
        }

        var deleteMessage = MessageModelMapper.INSTANCE.toDto(messageModel, authService);
        messageRepository.deleteById(request.getMessageId());
        return deleteMessage;
    }

    public List<Integer> getAnotherPartners(Integer userId, Integer conversationId) {
        var conversations = checkConversationHasPartner(conversationId, userId);
        return conversations.stream()
                .map(ConversationPartner::getPartnerId)
                .filter(partnerId -> !partnerId.equals(userId))
                .toList();
    }

    private List<ConversationPartner> checkConversationHasPartner(Integer conversationId, Integer partnerId) {
        var conversations = partnerRepository.findByConversationId(conversationId);
        if(conversations.stream().noneMatch(partner -> partner.getId().equals(partnerId))){
            log.error("user not match with conversation");
            throw new ApplicationException(ResponseCode.PARAMETER_VALUE_IS_INVALID, "user not match with conversation");
        }
        return conversations;
    }

    public Conversation createNewConversation(Integer ...partnerIds) {
        var conversation = conversationRepository.saveAndFlush(new Conversation());
        addToConversation(conversation.getId(), partnerIds);
        return conversation;
    }

    public void addToConversation(Integer conversationId, Integer ...partnerIds) {
        partnerRepository.saveAllAndFlush(
                Arrays.stream(partnerIds)
                        .filter(Objects::nonNull)
                        .map((partnerId) -> {
                            var newPartner = new ConversationPartner();
                            newPartner.setPartnerId(partnerId);
                            newPartner.setConversationId(conversationId);
                            return newPartner;
                        })
                        .toList()
        );
    }

    public MessageDto getMessageById(Integer messageId) {
        return MessageModelMapper.INSTANCE.toDto(
                getMessageModelById(messageId), authService
        );
    }

    private MessageModel getMessageModelById(Integer messageId){
        return messageRepository.findById(messageId).orElseThrow(
                () -> {
                    log.error("Message with id {} not found", messageId);
                    return new ApplicationException("Message with id " + messageId + " not found");
                }
        );
    }

    private Conversation getConversationByPartner(Integer senderId, Integer receiverId) {
        return conversationRepository.findInboxConversation(senderId, receiverId)
                .orElseGet(() -> createNewConversation(senderId, receiverId));
    }

    private Conversation getConversationById(Integer conversationId) {
        return conversationRepository.findById(conversationId).orElseThrow(
                () -> new NoDataException("conversationId does not exist")
        );
    }

}
