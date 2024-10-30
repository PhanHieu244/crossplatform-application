package vn.edu.hust.project.crossplatform.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.hust.project.crossplatform.dto.request.DeleteMessageRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetConversationRequest;
import vn.edu.hust.project.crossplatform.dto.request.GetListConversationRequest;
import vn.edu.hust.project.crossplatform.dto.request.SendMessageRequest;
import vn.edu.hust.project.crossplatform.dto.response.Resource;
import vn.edu.hust.project.crossplatform.service.IChatService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/it5023e")
public class ChatController {
    private final IChatService chatService;

    @MessageMapping("/message")
    public void sendMessage(
            @Payload SendMessageRequest message
    ) {
        chatService.sendMessage(message);
    }

    @PostMapping("/get_list_conversation")
    public ResponseEntity<Resource> getListConversation(
            @Valid @RequestBody GetListConversationRequest request
    ){
        return ResponseEntity.ok().body(
                new Resource(chatService.getListConversation(request))
        );
    }

    @PostMapping("/get_conversation")
    public ResponseEntity<Resource> getConversation(
            @Valid @RequestBody GetConversationRequest request
    ){
        return ResponseEntity.ok().body(
                new Resource(chatService.getConversation(request))
        );
    }

    @PostMapping("/delete_message")
    public ResponseEntity<Resource> deleteMessage(
            @Valid @RequestBody DeleteMessageRequest request
    ){
        return ResponseEntity.ok().body(
                new Resource(chatService.deleteMessage(request))
        );
    }
}
