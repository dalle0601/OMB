package com.project.omb.user.join.controller;

import com.project.omb.facade.JoinFacade;
import com.project.omb.user.join.dto.JoinRequestDTO;
import com.project.omb.user.join.dto.JoinResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class JoinController {
    private final JoinFacade joinFacade;

    @Autowired
    public JoinController(JoinFacade joinFacade) {
        this.joinFacade = joinFacade;
    }

    @PostMapping("/user/join")
    public JoinResponseDTO joinUser(@RequestBody JoinRequestDTO request) {
        joinFacade.join(request);
        return new JoinResponseDTO();
    }
}