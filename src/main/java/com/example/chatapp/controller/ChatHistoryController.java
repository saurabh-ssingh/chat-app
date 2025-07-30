package com.example.chatapp.controller;

import com.example.chatapp.model.ChatMessageEntity;
import com.example.chatapp.repository.ChatMessageRepository;
import com.example.chatapp.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class ChatHistoryController {

    private final ChatMessageRepository repository;

    /** inbox of a user */
    @GetMapping("/inbox/{username}")
    public ResponseEntity<GenericResponse<Page<ChatMessageEntity>>> inbox(
            @PathVariable String username,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        PageRequest pr = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<ChatMessageEntity> messages = repository.findByReceiverOrderByCreatedAtDesc(username, pr);
        return ResponseEntity.ok(GenericResponse.success(messages));
    }

    /** conversation between two users */
    @GetMapping("/conversation/{u1}/{u2}")
    public ResponseEntity<GenericResponse<Page<ChatMessageEntity>>> conversation(
            @PathVariable String u1,
            @PathVariable String u2,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        PageRequest pr = PageRequest.of(page, size);
        Page<ChatMessageEntity> messages = repository.findConversation(u1, u2, pr);
        return ResponseEntity.ok(GenericResponse.success(messages));
    }
}
