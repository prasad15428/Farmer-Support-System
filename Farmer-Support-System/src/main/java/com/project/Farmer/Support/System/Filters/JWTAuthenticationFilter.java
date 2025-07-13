package com.project.Farmer.Support.System.Filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Farmer.Support.System.Entity.UserAuthEntity;
import com.project.Farmer.Support.System.Utility.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JWTUtil jwtUtil){
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/auth/register".equals(servletPath)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (servletPath.startsWith("/h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }

        if ("/auth/login".equals(servletPath)) {
            ObjectMapper objectMapper = new ObjectMapper();
            UserAuthEntity userAuthEntity = objectMapper.readValue(request.getInputStream(), UserAuthEntity.class);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationFilter = new
                    UsernamePasswordAuthenticationToken(userAuthEntity.getUsername(), userAuthEntity.getPassword());
            Authentication authenticationResult = authenticationManager.authenticate(usernamePasswordAuthenticationFilter);
            if (authenticationResult.isAuthenticated()) {
                String token = jwtUtil.generateToken(userAuthEntity.getUsername(), 15);
                response.setHeader("Authorization", "Bearer " + token);
            }
        }
    }
}
