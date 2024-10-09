package com.loctran.service.common.security;

import com.loctran.service.exception.custom.ForbiddenException;
import com.loctran.service.entity.user.JwtService;
import com.loctran.service.entity.user.User;
import com.loctran.service.entity.user.UserRepository;
import com.loctran.service.exception.custom.ResourceNotFoundException;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
        System.out.println("trigger...");
        String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);
        String userEmail = jwtService.extractUsername(jwt);
        System.out.println("userEmail:"+userEmail);
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User","email",userEmail));
        if(jwtService.isTokenValid(jwt,user)  && SecurityContextHolder.getContext().getAuthentication()==null){
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
              user,
              null,
              user.getAuthorities()
          );
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);

          request.setAttribute("userId", user.getId());
        }
      }
    }
    catch (RuntimeException exception){
      throw new ForbiddenException(exception.getMessage());
    }
    filterChain.doFilter(request,response);
  }
}