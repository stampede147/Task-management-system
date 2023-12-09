package ru.ekudashov.taskms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ekudashov.taskms.dto.AccessTokenResponseDto;
import ru.ekudashov.taskms.dto.EmailPasswordRequestDto;
import ru.ekudashov.taskms.model.User;
import ru.ekudashov.taskms.repository.UserRepository;
import ru.ekudashov.taskms.service.AuthenticationService;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AccessTokenResponseDto authenticate(EmailPasswordRequestDto emailPasswordRequestDto) {

        final String email = emailPasswordRequestDto.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);

        final String password = emailPasswordRequestDto.getPassword();
        passwordEncoder.matches(password, user.getPassword());

        return null;
    }
}
