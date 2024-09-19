package com.poc.userauthentication.config;

import com.poc.userauthentication.service.JwtService;
import com.poc.userauthentication.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.poc.userauthentication.util.AppConstants.AUTHORIZATION;
import static com.poc.userauthentication.util.AppConstants.BEARER;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Lazy
    @Autowired
    private UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtAuthFilter :: doFilterInternal");
        String authHeader = request.getHeader(AUTHORIZATION);
        String token = null;
        String username = null;
        log.info("JwtAuthFilter :: check if authHeader available if yes extract username");
        if (authHeader != null && authHeader.startsWith(BEARER)) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }
        log.info("If username != null && SecurityContext does not contain AuthenticationContext");
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.info(" - loadUserByUsername :: {}", username);
            UserDetails userDetails = userService.loadUserByUsername(username);
            log.info("validateToken");
            if (jwtService.validateToken(token, userDetails).equals(Boolean.TRUE)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                log.info("Set security context");
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
