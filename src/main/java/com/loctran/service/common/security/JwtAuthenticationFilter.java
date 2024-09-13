package com.loctran.service.common.security;

import com.loctran.service.exception.custom.ForbiddenException;
import com.loctran.service.user.JwtService;
import com.loctran.service.user.User;
import com.loctran.service.user.UserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter   extends OncePerRequestFilter {

  private final UserRepository userRepository;
  private final JwtService jwtService;
  @Value("${application.security.jwt.secret-key}")
  private String secretKey;
  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException, JwtException {
    try{

      if(request.getHeader("Authorization")!=null && !request.getHeader("Authorization").isEmpty()){
        String authHeader = request.getHeader("Authorization");
        System.out.println("Auth:" + authHeader);
        String jwt = authHeader.substring(7);
        System.out.println(jwt);
        String userEmail = jwtService.extractUsername(jwt);
        System.out.println("userEmail:" + userEmail);
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if(jwtService.isTokenValid(jwt,userOptional.get())  && SecurityContextHolder.getContext().getAuthentication()==null){
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
              userOptional.get(),
              null,
              userOptional.get().getAuthorities()
          );
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
    }catch (RuntimeException exception){
      throw new ForbiddenException(exception.getMessage());
    }
    filterChain.doFilter(request,response);
  }
}