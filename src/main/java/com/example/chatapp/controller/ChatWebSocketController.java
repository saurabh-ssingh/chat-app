package com.example.chatapp.controller;

import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

//    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

//    public ChatWebSocketController(SimpMessagingTemplate template) {
//        this.messagingTemplate = template;
//    }
//
//    @MessageMapping("/chat") // client sends to /app/chat
//    public void handleChat(@Payload ChatMessage msg) {
//        // deliver to each receiver's personal queue
//        msg.getReceivers().forEach(r ->
//            messagingTemplate.convertAndSendToUser(r, "/queue/messages", msg)
//        );
//    }

    @MessageMapping("/chat") // client sends to /app/chat
    public void handleChat(@Payload ChatMessage msg, Principal principal) {
        // Use authenticated principal if available, else fallback to payload
        String sender = principal != null ? principal.getName() : msg.getSender();
        chatService.saveAndDispatch(sender, msg);
    }
}
