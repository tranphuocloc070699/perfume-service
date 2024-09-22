package com.loctran.service.entity.user;

import com.loctran.service.common.CommonService;
import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.media.MediaType;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.product.ProductService;
import com.loctran.service.entity.product.dto.CreateProductDto;
import com.loctran.service.entity.product.dto.UpdateProductDto;
import com.loctran.service.entity.user.dto.JWTResponseDto;
import com.loctran.service.entity.user.dto.UserLoginDto;
import com.loctran.service.entity.user.dto.UserRegisterDto;
import com.loctran.service.exception.custom.ForbiddenException;
import com.loctran.service.entity.user.dto.UserUpdateDto;
import com.loctran.service.utils.FileUploadUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.io.IOException;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final JwtService jwtService;
  private final CommonService commonService;
  private final ProductService productService;

  @PostMapping("/signup")
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

  @GetMapping("")
  public Object authenticate(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) {
    String token = jwtService.getCookie(httpServletRequest);
    String email = jwtService.extractRefreshTokenUsername(token);
    User user = userService.findByEmail(email);
    if (!jwtService.isRefreshTokenValid(token, user)) {
      throw new ForbiddenException("Token invalid");
    }
    String accessToken = jwtService.generateToken(user);
    ResponseDto responseDto = ResponseDto.builder().build();
    JWTResponseDto jwtResponseDto = JWTResponseDto.builder().data(user).accessToken(accessToken)
        .build();
    responseDto.setMessage("Xác thực thông tin người dùng thành công");
    responseDto.setStatus(200);
    responseDto.setData(jwtResponseDto);
    String refreshToken = jwtService.generateRefreshToken(user);
    jwtService.writeCookie(refreshToken, httpServletResponse);
    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> update(@PathVariable("id") Long id,
      @RequestBody UserUpdateDto dto) {
    User user = userService.update(id, dto);
    ResponseDto responseDto = ResponseDto.builder()
        .message("Cập nhật thông tin người dùng thành công").status(200).data(user).build();

    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("/upload")
  public ResponseEntity<ResponseDto> upload(@RequestParam("image") MultipartFile multipartFile,
      @RequestParam("id") String id) {
    String filename = multipartFile.getOriginalFilename();
    String uploadDir = "src/main/resources/static/upload/" + id;
    try {
      String path = FileUploadUtil.saveFile(uploadDir, filename, multipartFile);

      Media media = Media.builder().type(MediaType.USER_AVATAR).path(path).build();

      ResponseDto responseDto = ResponseDto.builder()
          .message("Cập nhật thông tin người dùng thành công").status(200).data(media).build();

      return ResponseEntity.ok(responseDto);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


}
