package com.project.omb.controller;

import com.project.omb.facade.UserFacade;
import com.project.omb.dto.UserReqDTO;
import com.project.omb.dto.UserResDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<UserResDTO> joinUser(@RequestBody UserReqDTO userReqDTO) {
        UserResDTO userResDTO = userFacade.registerUser(userReqDTO);
        return ResponseEntity.ok(userResDTO);
    }
}