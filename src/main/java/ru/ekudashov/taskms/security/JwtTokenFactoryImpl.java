package ru.ekudashov.taskms.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;

@Slf4j
@Component
public class JwtTokenFactoryImpl implements TokenFactory {

    protected String jwtSecret;

    protected int tokenLifeMillis;

    private final Algorithm algorithm;

    private final JWTVerifier tokenVerifier;

    public JwtTokenFactoryImpl(@Value("${application.security.jwt.secret}") String jwtSecret,
                               @Value("${application.security.jwt.lifeMillis}") int tokenLifeMillis) {
        this.jwtSecret = jwtSecret;
        this.tokenLifeMillis = tokenLifeMillis;
        this.algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        this.tokenVerifier = JWT.require(algorithm).build();

    }

    @Override
    public String createToken(String name, Collection<? extends String> authorities) {
        log.trace("Started createToken(String, Collection> method");
        log.debug("Provided parameters name: {}, authorities: {}", name, authorities);

        return JWT.create()
                .withClaim(JwtTokenConstants.SUB, name)
                .withArrayClaim(JwtTokenConstants.ROLES, authorities.toArray(String[]::new))
                .withClaim(JwtTokenConstants.ISSUED_AT, Instant.now())
                .withClaim(JwtTokenConstants.EXPIRES_AT, Instant.now().plusMillis(tokenLifeMillis))
                .sign(algorithm);
    }

    @Override
    public AbstractAuthenticationToken decodeToken(String rawToken) throws JWTVerificationException {
        log.trace("Started validateAndDecodeToken(String) method");
        log.trace("Started decoding token");

        DecodedJWT decoded = tokenVerifier.verify(rawToken);

        return new UsernamePasswordAuthenticationToken(decoded.getSubject(),
                null,
                decoded.getClaim(JwtTokenConstants.ROLES).asList(GrantedAuthority.class));
    }

}
