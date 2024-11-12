package com.project.omb.controller;

import com.project.omb.dto.LoginReqDTO;
import com.project.omb.facade.UserFacade;
import com.project.omb.dto.UserReqDTO;
import com.project.omb.dto.UserResDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping("/user/register")
    public ResponseEntity<UserResDTO> joinUser(@Valid @RequestBody UserReqDTO userReqDTO) {
        UserResDTO userResDTO = userFacade.registerUser(userReqDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResDTO); // 상태 코드를 CREATED로 설정
    }

    @PostMapping("/user/login")
    public ResponseEntity<UserResDTO> loginUser(@Valid @RequestBody LoginReqDTO loginReqDTO) {
        UserResDTO userResDTO = userFacade.loginUser(loginReqDTO);
        return ResponseEntity.ok(userResDTO);
    }
}