package com.example.chatapp.controller;

import com.example.chatapp.dto.UserDto;
import com.example.chatapp.response.GenericResponse;
import com.example.chatapp.service.ChatUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ChatUserService chatUserService;

    public UserController(ChatUserService chatUserService) {
        this.chatUserService = chatUserService;

    }

    @Operation(summary = "Register a new user")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @PostMapping("/register")
    public ResponseEntity<GenericResponse<UserDto>> createUser(@Valid @RequestBody UserDto user) {
       final UserDto userDto = this.chatUserService.createUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(userDto));

    }
}
