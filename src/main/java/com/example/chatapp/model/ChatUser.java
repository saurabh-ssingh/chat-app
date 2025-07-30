package com.example.chatapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "chat_users")
public class ChatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Cognito username must not be blank")
    @Size(max = 100, message = "Cognito username must not exceed 100 characters")
    @Column(name = "cognito_user_name",  nullable = false, length = 100)
    private String cognitoUsername;

    @Size(max = 100, message = "Display name must not exceed 100 characters")
    @Column(name = "display_name", length = 100)
    private String displayName;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email should be valid")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Size(max = 255, message = "Profile picture URL must not exceed 255 characters")
    @Column(name = "profile_pic_url", length = 255)
    private String profilePictureUrl;

    @Column(name = "online")
    private Boolean online = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_seen")
    private LocalDateTime lastSeen;

    @Column(name = "is_active")
    private Boolean isActive = true;

}
