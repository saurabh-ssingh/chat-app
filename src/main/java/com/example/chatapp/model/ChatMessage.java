package com.example.chatapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatMessage {
    private String sender;
    private List<String> receivers;
    private String content;
}
