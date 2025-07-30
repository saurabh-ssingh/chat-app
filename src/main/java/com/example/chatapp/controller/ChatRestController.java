package com.example.chatapp.controller;

import com.example.chatapp.model.ChatMessageEntity;
import com.example.chatapp.repository.ChatMessageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/messages")
//@CrossOrigin // Allow frontend CORS if needed
//public class ChatRestController {
//
//    private final ChatMessageRepository repository;
//
//    public ChatRestController(ChatMessageRepository repository) {
//        this.repository = repository;
//    }
//
//    // Get all messages sent to a specific user
//    @GetMapping("/{username}")
//    public List<ChatMessageEntity> getMessagesForUser(@PathVariable String username) {
//        return repository.findByReceiver(username);
//    }
//
//    // Get conversation between two users
//    @GetMapping("/conversation/{user1}/{user2}")
//    public List<ChatMessageEntity> getConversation(@PathVariable String user1, @PathVariable String user2) {
//        return repository.findConversation(user1, user2);
//    }
//
//    // (Optional) Delete all messages
//    @DeleteMapping("/all")
//    public void deleteAllMessages() {
//        repository.deleteAll();
//    }
//}
