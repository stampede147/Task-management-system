package ru.ekudashov.taskms.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import ru.ekudashov.taskms.model.Role;

import java.security.Principal;
import java.util.Collection;

public interface TokenFactory {

    public String createToken(String name, Collection<? extends String> authorities);


    public AbstractAuthenticationToken decodeToken(String token);
}
