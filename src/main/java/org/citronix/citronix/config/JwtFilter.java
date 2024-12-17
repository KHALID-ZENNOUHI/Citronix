package org.citronix.citronix.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.citronix.citronix.service.impl.JwtService;
import org.citronix.citronix.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            logger.info("Authorization Header: " + authorizationHeader);

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);

                try {
                    String username = jwtService.extractUsername(token);
                    logger.info("Extracted Username: " + username);


                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = applicationContext.getBean(MyUserDetailsService.class).loadUserByUsername(username);

                        logger.info("Authorization Header: {}" + authorizationHeader);
                        if (jwtService.validateToken(token, userDetails)) {
                            UsernamePasswordAuthenticationToken authToken =
                                    new UsernamePasswordAuthenticationToken(
                                            userDetails,
                                            null,
                                            userDetails.getAuthorities()
                                    );

                            authToken.setDetails(
                                    new WebAuthenticationDetailsSource().buildDetails(request)
                            );

                            SecurityContextHolder.getContext().setAuthentication(authToken);

                            logger.info("User Authorities: " + userDetails.getAuthorities());
                        } else {
                            logger.warn("Token validation failed");
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                            return;
                        }
                    }
                } catch (Exception e) {
                    logger.error("Token processing error", e);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token processing error");
                    return;
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            logger.error("Unexpected error in JWT filter", e);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
        }
    }
}
