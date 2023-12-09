package ru.ekudashov.taskms.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class BearerAuthenticationFilter extends OncePerRequestFilter {

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private final BearerAuthenticationConverter converter = new BearerAuthenticationConverter();
    private final AuthenticationManager authenticationManager;
    protected AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    public BearerAuthenticationFilter(AuthenticationManager manager) {
        this.authenticationManager = manager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!requiresAuthentication(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            BearerAuthenticationToken bearerAuthentication = converter.convert(request);

            bearerAuthentication.setDetails(authenticationDetailsSource.buildDetails(request));

            Authentication authentication = authenticationManager.authenticate(bearerAuthentication);

            onSuccessfulAuthentication(request, response, filterChain, authentication);
        } catch (AuthenticationException e) {
            onUnsuccessfulAuthentication(request, response, filterChain);
        }

        filterChain.doFilter(request, response);
    }

    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        this.securityContextHolderStrategy.clearContext();
    }

    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) {
        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(context);
    }

    protected boolean requiresAuthentication(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION) != null;
    }
}
