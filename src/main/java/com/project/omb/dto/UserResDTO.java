package com.project.omb.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResDTO {
    private String userName;
    private String email;
}
