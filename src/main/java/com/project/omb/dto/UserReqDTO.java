package com.project.omb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserReqDTO{
    @NotBlank(message = "UserName is required")
    private String userName;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "gender is required")
    private String gender;
}
