package com.example.jwtgayclublesson.auth;

import com.example.jwtgayclublesson.dto.AuthOrRegisterResponseDto;
import com.example.jwtgayclublesson.dto.AuthRequestDto;
import com.example.jwtgayclublesson.dto.RegisterRequestDto;
import com.example.jwtgayclublesson.model.User;
import com.example.jwtgayclublesson.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthOrRegisterService {
    private final UsersRepository usersRepository;
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthOrRegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        User user = User.builder()
                .name(registerRequestDto.getName())
                .email(registerRequestDto.getEmail())
                .username(registerRequestDto.getUsername())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .build();

        usersRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return AuthOrRegisterResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthOrRegisterResponseDto authenticate(AuthRequestDto authRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getUsername(),
                        authRequestDto.getPassword()
                )
        );

        User user = usersRepository.findByUsername(authRequestDto.getUsername())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        return AuthOrRegisterResponseDto.builder()
                .token(jwtToken)
                .build();
    }


}
