package com.project.omb.service;

import com.project.omb.domain.User;
import com.project.omb.dto.UserReqDTO;
import com.project.omb.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
