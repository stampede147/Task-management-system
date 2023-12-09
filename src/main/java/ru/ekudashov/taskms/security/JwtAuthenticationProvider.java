package ru.ekudashov.taskms.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    public static final String JWT_TOKEN_FACTORY = "jwtTokenFactory";

    private final TokenFactory factory;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BearerAuthenticationToken bearerToken = (BearerAuthenticationToken) authentication;

        AbstractAuthenticationToken abstractAuthenticationToken;
            abstractAuthenticationToken = factory.decodeToken(bearerToken.getRawToken());
//        try {
//        } catch ( e) {
//            throw new InternalAuthenticationServiceException("problems with decoding jwt token", e);
//        }

        abstractAuthenticationToken.setDetails(bearerToken.getDetails());
        abstractAuthenticationToken.setAuthenticated(true);

        return abstractAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return BearerAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
