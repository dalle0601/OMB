package com.project.omb.facade;

import com.project.omb.domain.User;
import com.project.omb.dto.UserReqDTO;
import com.project.omb.dto.UserResDTO;
import com.project.omb.service.AuthService;
import com.project.omb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public UserFacade(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    public UserResDTO registerUser(UserReqDTO userReqDTO) {
//        String encryptedPassword = authService.encryptPassword(userReqDTO.getPassword());
        User user = new User(userReqDTO.getUserName(), userReqDTO.getPassword(), userReqDTO.getEmail(), userReqDTO.getGender());
        userService.saveUser(user);
        return UserResDTO.builder()
                            .userName(user.getUserName())
                            .email(user.getEmail())
                            .build();
    }
}