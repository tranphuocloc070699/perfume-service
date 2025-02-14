package com.loctran.service.entity.user.dto;

import com.loctran.service.entity.user.Role;
import com.loctran.service.entity.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
  @NotBlank(message = "Trường này không được để trống")
  @Email(message = "Email không hợp lệ")
  private String email;

  @NotBlank(message = "Trường này không được để trống")
  private String name;

  @NotBlank(message = "Trường này không được để trống")
  @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
  private String password;

  public User mapToUser(){
    return User.builder().role(Role.USER).avatar(null).email(this.email).name(this.name).password(
        this.password).build();
  }
}