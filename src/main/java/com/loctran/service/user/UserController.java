package com.loctran.service.user;

import com.loctran.service.common.CommonService;
import com.loctran.service.common.ResponseDto;
import com.loctran.service.exception.custom.ForbiddenException;
import com.loctran.service.product.Product;
import com.loctran.service.product.ProductService;
import com.loctran.service.product.dto.CreateProductDto;
import com.loctran.service.user.dto.JWTResponseDto;
import com.loctran.service.user.dto.UserLoginDto;
import com.loctran.service.user.dto.UserRegisterDto;
import com.loctran.service.user.dto.UserUpdateDto;
import com.loctran.service.utils.FileUploadUtil;
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

  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> update(@PathVariable("id") String id,
      @RequestBody UserUpdateDto dto) {
    User user = userService.update(id, dto);
    ResponseDto responseDto = ResponseDto.builder().path("/user/update/" + id)
        .message("Cập nhật thông tin người dùng thành công").status(200).data(user).build();

    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("/upload")
  public String upload(@RequestParam("image") MultipartFile multipartFile,
      @RequestParam("id") String id) {
    String filename = multipartFile.getOriginalFilename();
    String uploadDir = "src/main/resources/static/upload/" + id;

    try {
      FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
      return filename;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /*Temp*/
  @GetMapping("/product/{slug}")
  public ResponseEntity<ResponseDto> findProductBySlug(@PathVariable("slug")String slug){
    Product product = productService.findProductBySlug(slug);
    ResponseDto responseDto = ResponseDto.builder().path("/user/product").build();

    responseDto.setMessage("Lấy thông tin sản phẩm thành công");
    responseDto.setStatus(200);
    responseDto.setData(product);

    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("/product")
  public ResponseEntity<ResponseDto> createProduct(@RequestBody CreateProductDto dto){
    Product product = productService.createProduct(dto);

    ResponseDto responseDto = ResponseDto.builder().path("/admin/product").build();

    responseDto.setMessage("Tạo sản phẩm thành công");
    responseDto.setStatus(200);
    responseDto.setData(product);

    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/product/{slug}")
  public ResponseEntity<ResponseDto> updateProduct(@PathVariable("slug")String slug,@RequestBody ){
    Product product = productService.findProductBySlug(slug);
    ResponseDto responseDto = ResponseDto.builder().path("/user/product").build();

    responseDto.setMessage("Lấy thông tin sản phẩm thành công");
    responseDto.setStatus(200);
    responseDto.setData(product);

    return ResponseEntity.ok(responseDto);
  }


}
