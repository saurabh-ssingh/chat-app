package com.example.chatapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "chat_messages",
        indexes = {
                @Index(name = "idx_receiver_created", columnList = "receiver, created_at"),
                @Index(name = "idx_sr_created", columnList = "sender, receiver, created_at")
        }
)
public class ChatMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** who sent the message (your Cognito username or email, choose one and be consistent) */
    @Column(nullable = false, length = 100)
    private String sender;

    /** single message row per receiver */
    @Column(nullable = false, length = 100)
    private String receiver;

    @NotBlank(message = "Message cannot be blank")
    @Size(max = 2000, message = "Message must not exceed 2000 characters")
    @Column(nullable = false, length = 2000)
    private String content;

    /** optional flags for UX features */
    @Column(name = "is_read", nullable = false)
    private boolean read = false;


    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
}
