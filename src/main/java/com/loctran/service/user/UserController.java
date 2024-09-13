package com.loctran.service.user;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.exception.custom.AlreadyExistException;
import com.loctran.service.user.dto.JWTResponseDto;
import com.loctran.service.user.dto.UserRegisterDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final JwtService jwtService;
  @PostMapping("/signup")
  public Object signup(@RequestBody @Valid UserRegisterDto dto, BindingResult bindingResult) throws Exception {
    if(bindingResult.hasErrors()){
      throw new BindException(bindingResult);
    }
    ResponseDto responseDto = ResponseDto.builder().path("/signup").build();
    User user = userService.register(dto);
    String accessToken = jwtService.generateToken(user);
    JWTResponseDto jwtResponseDto = JWTResponseDto.builder().data(user).accessToken(accessToken).build();
    responseDto.setMessage("Đăng ký tài khoản thành công");
    responseDto.setStatus(200);
    responseDto.setData(jwtResponseDto);

    return ResponseEntity.ok(responseDto);
  }
}
