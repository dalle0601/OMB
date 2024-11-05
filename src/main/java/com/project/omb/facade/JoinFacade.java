package com.project.omb.facade;

import com.project.omb.user.join.dto.JoinRequestDTO;
import com.project.omb.user.join.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinFacade {
    private final JoinService joinService;

    @Autowired
    public JoinFacade(JoinService joinService) {
        this.joinService = joinService;
    }

    public void join(JoinRequestDTO request) {
        joinService.registerUser(request);
    }
}