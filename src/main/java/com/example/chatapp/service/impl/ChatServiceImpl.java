package com.example.chatapp.service.impl;

import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.model.ChatMessageEntity;
import com.example.chatapp.repository.ChatMessageRepository;
import com.example.chatapp.service.ChatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatMessageRepository repository;
    private final SimpMessagingTemplate messagingTemplate;

    @Transactional
    @Override
    public void saveAndDispatch(String sender, ChatMessage msg) {
        for (String receiver : msg.getReceivers()) {
            // persist
            ChatMessageEntity entity = ChatMessageEntity.builder()
                    .sender(sender)
                    .receiver(receiver)
                    .content(msg.getContent())
                    .read(false)
                    .build();
            repository.save(entity);

            // deliver over WebSocket to the receiver’s personal queue
            messagingTemplate.convertAndSendToUser(receiver, "/queue/messages", msg);
        }

    }
}
