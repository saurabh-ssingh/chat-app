package com.example.chatapp.service.impl;

import com.example.chatapp.dto.UserDto;
import com.example.chatapp.exception.AppException;
import com.example.chatapp.model.ChatUser;
import com.example.chatapp.repository.ChatUserRepository;
import com.example.chatapp.service.ChatUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatUserServiceImpl implements ChatUserService {

    private final ChatUserRepository chatUserRepository;

    public ChatUserServiceImpl(ChatUserRepository chatUserRepository){
        this.chatUserRepository = chatUserRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        final ChatUser chatUser =this.chatUserRepository.findByEmailAndIsActive(userDto.getEmail(),true);
        if(chatUser != null){
            throw new AppException("User is already exist","100-10", HttpStatus.BAD_REQUEST);
        }

        ChatUser newUser = ChatUser.builder()
                .cognitoUsername("cognito_user_name2")
                .displayName("dispaly_name")
                .email(userDto.getEmail())
                .profilePictureUrl("user_profile_pic")
                .online(false)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();

        this.chatUserRepository.save(newUser);
        return new UserDto(userDto.getEmail(), userDto.getPassword());
    }
}
