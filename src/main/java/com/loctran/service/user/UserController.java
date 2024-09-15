package com.loctran.service.user;

import com.loctran.service.common.CommonService;
import com.loctran.service.common.ResponseDto;
import com.loctran.service.exception.custom.AlreadyExistException;
import com.loctran.service.exception.custom.ForbiddenException;
import com.loctran.service.user.dto.JWTResponseDto;
import com.loctran.service.user.dto.UserLoginDto;
import com.loctran.service.user.dto.UserRegisterDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final JwtService jwtService;
  private final CommonService commonService;

  @PostMapping("/signup")
  public Object signup(@RequestBody @Valid UserRegisterDto dto, BindingResult bindingResult,
      HttpServletResponse httpServletResponse) throws Exception {
    commonService.validate(bindingResult);
    ResponseDto responseDto = ResponseDto.builder().path("/user/signup").build();
    User user = userService.register(dto);
    String accessToken = jwtService.generateToken(user);
    JWTResponseDto jwtResponseDto = JWTResponseDto.builder().data(user).accessToken(accessToken)
        .build();
    responseDto.setMessage("Đăng ký tài khoản thành công");
    responseDto.setStatus(200);
    responseDto.setData(jwtResponseDto);

    String refreshToken = jwtService.generateRefreshToken(user);
    jwtService.writeCookie(refreshToken, httpServletResponse);

    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("/login")
  public Object login(@RequestBody @Valid UserLoginDto dto, BindingResult bindingResult,
      HttpServletResponse httpServletResponse) throws Exception {
    commonService.validate(bindingResult);

    ResponseDto responseDto = ResponseDto.builder().path("/user/login").build();
    User user = userService.login(dto);
    String accessToken = jwtService.generateToken(user);
    JWTResponseDto jwtResponseDto = JWTResponseDto.builder().data(user).accessToken(accessToken)
        .build();
    responseDto.setMessage("Đăng nhập thành công");
    responseDto.setStatus(200);
    responseDto.setData(jwtResponseDto);

    String refreshToken = jwtService.generateRefreshToken(user);
    jwtService.writeCookie(refreshToken, httpServletResponse);

    return ResponseEntity.ok(responseDto);
  }

  @GetMapping("/")
  public Object authenticate(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) {
    String token = jwtService.getCookie(httpServletRequest);
    String email = jwtService.extractRefreshTokenUsername(token);
    User user = userService.findByEmail(email);
    if (!jwtService.isRefreshTokenValid(token, user)) {
      throw new ForbiddenException("Token invalid");
    }
    String accessToken = jwtService.generateToken(user);
    ResponseDto responseDto = ResponseDto.builder().path("/user/authenticate").build();
    JWTResponseDto jwtResponseDto = JWTResponseDto.builder().data(user).accessToken(accessToken)
        .build();
    responseDto.setMessage("Xác thực thông tin người dùng thành công");
    responseDto.setStatus(200);
    responseDto.setData(jwtResponseDto);
    String refreshToken = jwtService.generateRefreshToken(user);
    jwtService.writeCookie(refreshToken, httpServletResponse);
    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/")
  public User update(){
    return null;
  }
}
