package com.bor.rcms.security;

import com.bor.rcms.entity.UserEntity;
import com.bor.rcms.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String token = null;
        String tokenSubject = null;

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                tokenSubject = jwtUtil.extractUsername(token); // This is phoneNumber or userName
            }

            if (tokenSubject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Try to load user by phoneNumber first
                UserEntity user = userRepository.findByPhoneNumber(tokenSubject);

                if (user == null) {
                    // Fallback to username
                    user = userRepository.findByUserName(tokenSubject).orElse(null);
                }

                if (user != null && jwtUtil.isTokenValid(token, tokenSubject)) {
                    // Create Spring Security user with role
                	User userDetails = new User(
                		    tokenSubject, "", // this matches the subject used in the token (userName or phoneNumber)
                		    Collections.singletonList(
                		        new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName())
                		    )
                		);


                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            chain.doFilter(request, response);

        } catch (ExpiredJwtException | SignatureException | IllegalArgumentException e) {
            // Forward exception to entry point if configured
            request.setAttribute("exception", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }
}
