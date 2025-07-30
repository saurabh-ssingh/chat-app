package com.example.chatapp.service;

import com.example.chatapp.model.ChatMessage;

public interface ChatService {
    void saveAndDispatch(String sender, ChatMessage msg);
}
