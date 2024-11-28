package org.example.backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.backend.repository.UserRepo;
import org.example.backend.service.jwt.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomFilter extends OncePerRequestFilter {

    final private UserRepo userRepo;
    final private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = request.getHeader("token");
            if (token != null) {
                String id = jwtService.extractJwtToken(token);
                UserDetails user = userRepo.findById(Long.parseLong(id)).orElseThrow();
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities()));
            }
        }catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        filterChain.doFilter(request, response);
    }
}
