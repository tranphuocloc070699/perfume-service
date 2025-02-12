package com.loctran.service.entity.user;

import com.loctran.service.common.CommonService;
import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.product.ProductService;
import com.loctran.service.entity.user.dto.JWTResponseDto;
import com.loctran.service.entity.user.dto.UserLoginDto;
import com.loctran.service.entity.user.dto.UserRegisterDto;
import com.loctran.service.exception.custom.ForbiddenException;
import com.loctran.service.entity.user.dto.UserUpdateDto;
import com.loctran.service.utils.FileUploadUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Tag(name = "User",description = "Operations related to User management")
public class UserController {

  private final UserService userService;
  private final JwtService jwtService;
  private final CommonService commonService;

  @GetMapping("")
  @Operation(
      summary = "Authenticate User",
      description = "Validates the user's refresh token from cookies. If the token is valid, a new access token is generated, and the refresh token is updated. If the token is expired or invalid, an appropriate error is returned."
  )
  public Object authenticate(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) {
    ResponseDto responseDto = ResponseDto.builder().build();
    String token = jwtService.getCookie(httpServletRequest);
    if (token == null) {
      responseDto.setStatus(400);
      responseDto.setMessage("Không tìm thấy token");
      responseDto.setData(null);
      return responseDto;
    }
    String email = "";
    try {
      email = jwtService.extractRefreshTokenUsername(token);
    } catch (ExpiredJwtException e) {
      jwtService.deleteCookie(httpServletResponse);
      throw new ForbiddenException("Token expired");
    }
    User user = userService.findByEmail(email);
    if (!jwtService.isRefreshTokenValid(token, user)) {
      throw new ForbiddenException("Token invalid");
    }
    String accessToken = jwtService.generateToken(user);

    JWTResponseDto jwtResponseDto = JWTResponseDto.builder()
        .data(user)
        .accessToken(accessToken)
        .build();

    responseDto.setMessage("Xác thực thông tin người dùng thành công");
    responseDto.setStatus(200);
    responseDto.setData(jwtResponseDto);

    String refreshToken = jwtService.generateRefreshToken(user);
    jwtService.writeCookie(refreshToken, httpServletResponse);

    return ResponseEntity.ok(responseDto);
  }



  @PostMapping("/signup")
  @Operation(summary = "User Registration", description = "Registers a new user with the provided details.")
  public Object signup(@RequestBody @Valid UserRegisterDto dto, BindingResult bindingResult,
      HttpServletResponse httpServletResponse) throws Exception {
    commonService.validate(bindingResult);
    ResponseDto responseDto = ResponseDto.builder().build();
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
  @Operation(
      summary = "User Login",
      description = "Authenticates a user with email and password. If credentials are valid, an access token is returned in the response, and a refresh token is stored in a cookie for session management."
  )
  public Object login(@RequestBody @Valid UserLoginDto dto, BindingResult bindingResult,
      HttpServletResponse httpServletResponse) throws Exception {

    commonService.validate(bindingResult);

    ResponseDto responseDto = ResponseDto.builder().build();
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

  @Operation(
      summary = "Update User Information",
      description = "Updates the information of an existing user identified by their ID. Returns the updated user details upon success."
  )
  public ResponseEntity<ResponseDto> update(@PathVariable("id") Long id,
      @RequestBody UserUpdateDto dto) {
    User user = userService.update(id, dto);
    ResponseDto responseDto = ResponseDto.builder()
        .message("Cập nhật thông tin người dùng thành công").status(200).data(user).build();

    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("/log-out")
  @Operation(
      summary = "User Logout",
      description = "Logs out the authenticated user by validating their refresh token and deleting the authentication cookie. Returns a success message upon successful logout."
  )
  public Object logout(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) {
    String token = jwtService.getCookie(httpServletRequest);
    String email = jwtService.extractRefreshTokenUsername(token);
    User user = userService.findByEmail(email);
    if (!jwtService.isRefreshTokenValid(token, user)) {
      throw new ForbiddenException("Token invalid");
    }
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Đăng xuất thành công");
    responseDto.setStatus(200);
    responseDto.setData(null);
    jwtService.deleteCookie(httpServletResponse);
    return ResponseEntity.ok(responseDto);
  }



}
