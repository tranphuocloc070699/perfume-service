package com.loctran.service.entity.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private final String accessTokenSecretKey;
  private final String refreshTokenSecretKey;
  private final long jwtExpiration;
  private final String refreshTokenName;
  private final long refreshExpiration;

  public JwtService(@Value("${application.security.jwt.secret-key}") String accessTokenSecretKey,
      @Value("${application.security.jwt.expiration}") long jwtExpiration,
      @Value("${application.security.jwt.refresh-token.expiration}") long refreshExpiration,
      @Value("${application.security.jwt.refresh-token.name}") String refreshTokenName,
      @Value("${application.security.jwt.refresh-token.secret-key}") String refreshTokenSecretKey
  ) {
    this.accessTokenSecretKey = accessTokenSecretKey;
    this.jwtExpiration = jwtExpiration;
    this.refreshExpiration = refreshExpiration;
    this.refreshTokenName = refreshTokenName;
    this.refreshTokenSecretKey = refreshTokenSecretKey;
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject,accessTokenSecretKey);
  }

  public String extractRefreshTokenUsername(String token) {
    return extractClaim(token, Claims::getSubject,refreshTokenSecretKey);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver,String secretKey) {
    final Claims claims = extractAllClaims(token,secretKey);
    return claimsResolver.apply(claims);
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(
      Map<String, Object> extraClaims,
      UserDetails userDetails
  ) {
    return buildToken(extraClaims, userDetails, jwtExpiration,accessTokenSecretKey);
  }

  public String generateRefreshToken(
      UserDetails userDetails
  ) {
    return buildToken(new HashMap<>(), userDetails, refreshExpiration,refreshTokenSecretKey);
  }

  private String buildToken(
      Map<String, Object> extraClaims,
      UserDetails userDetails,
      long expiration,
      String key
  ) {
    return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSignInKey(key), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && isTokenExpired(token,
        accessTokenSecretKey);
  }

  public boolean isRefreshTokenValid(String token, UserDetails userDetails) {
    final String username = extractRefreshTokenUsername(token);
    return (username.equals(userDetails.getUsername())) && isTokenExpired(token,
        refreshTokenSecretKey);
  }

  private boolean isTokenExpired(String token,String secretKey) {
    return !extractExpiration(token, secretKey).before(new Date());
  }

  private Date extractExpiration(String token,String secretKey) {
    return extractClaim(token, Claims::getExpiration,secretKey);
  }

  private Claims extractAllClaims(String token,String secretKey) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey(secretKey))
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSignInKey(String key) {
    byte[] keyBytes = Decoders.BASE64.decode(key);
    return Keys.hmacShaKeyFor(keyBytes);
  }


  public void writeCookie(String token, HttpServletResponse response) {
    Cookie cookie = new Cookie(refreshTokenName, token);
    cookie.setMaxAge((int) refreshExpiration);
    cookie.setSecure(false);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    response.addCookie(cookie);
  }

  public void deleteCookie(HttpServletResponse response) {
    Cookie cookie = new Cookie(refreshTokenName, null);
    cookie.setMaxAge(0); // This deletes the cookie
    cookie.setSecure(false);
    cookie.setHttpOnly(true);
    cookie.setPath("/"); // Ensure this matches the original cookie's path
    response.addCookie(cookie);
  }

  public String getCookie(HttpServletRequest request) {

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {

      for (Cookie cookie : cookies) {
        if (refreshTokenName.equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }
    return null;
  }
}