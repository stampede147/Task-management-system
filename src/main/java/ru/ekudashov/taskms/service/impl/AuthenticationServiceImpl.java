package ru.ekudashov.taskms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.ekudashov.taskms.dto.AccessTokenResponseDto;
import ru.ekudashov.taskms.dto.EmailPasswordRequestDto;
import ru.ekudashov.taskms.model.User;
import ru.ekudashov.taskms.repository.UserRepository;
import ru.ekudashov.taskms.security.TokenFactory;
import ru.ekudashov.taskms.service.AuthenticationService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenFactory tokenFactory;

    @Override
    @Transactional(readOnly = true)
    public AccessTokenResponseDto authenticate(EmailPasswordRequestDto emailPasswordRequestDto) {

        User user = userRepository.findByEmail(emailPasswordRequestDto.getEmail())
                .orElseThrow(RuntimeException::new);

        if (!passwordEncoder.matches(emailPasswordRequestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("password is wrong");
        }

        return new AccessTokenResponseDto(tokenFactory.createToken(String.valueOf(user.getId()), user.getAuthorities().stream().map(Enum::name).collect(Collectors.toList())));
    }
}
