package com.example.chatapp.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse<T>{
    private T data;
    private String message;
    private Boolean success;

    public static <T> GenericResponse<T> success(T data) {
        return GenericResponse.<T>builder()
                .data(data)
                .message("Success")
                .success(true)
                .build();
    }

    public static <T> GenericResponse<T> error(T data) {
        return GenericResponse.<T>builder()
                .data(data)
                .message("ERROR")
                .success(false)
                .build();
    }
}



