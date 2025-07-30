package com.example.chatapp.repository;

import com.example.chatapp.model.ChatMessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {

    /** messages received by a user */
    Page<ChatMessageEntity> findByReceiverOrderByCreatedAtDesc(String receiver, Pageable pageable);

    /** bi-directional conversation (user1 <-> user2) */
    @Query("""
           SELECT m FROM ChatMessageEntity m
           WHERE (m.sender = :u1 AND m.receiver = :u2)
              OR (m.sender = :u2 AND m.receiver = :u1)
           ORDER BY m.createdAt DESC
           """)
    Page<ChatMessageEntity> findConversation(@Param("u1") String user1,
                                             @Param("u2") String user2,
                                             Pageable pageable);
}
