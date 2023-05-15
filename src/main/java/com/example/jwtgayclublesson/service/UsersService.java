package com.example.jwtgayclublesson.service;

import com.example.jwtgayclublesson.dto.UserResponseDto;
import com.example.jwtgayclublesson.model.User;
import com.example.jwtgayclublesson.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public UserResponseDto getUserByUsername(String username) throws Exception {
        Optional<User> userResult = usersRepository.findByUsername(username);

        if (userResult.isEmpty()) {
            throw new Exception("User not found");
        }

        User user = userResult.get();

        return UserResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
