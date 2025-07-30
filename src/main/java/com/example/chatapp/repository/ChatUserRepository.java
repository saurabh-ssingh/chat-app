package com.example.chatapp.repository;

import com.example.chatapp.model.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser,Long> {
    ChatUser findByEmailAndIsActive(String email,Boolean isActive);
}
