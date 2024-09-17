package com.loctran.service.entity.user.dto;

import com.loctran.service.entity.user.Role;
import com.loctran.service.entity.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {
  @NotBlank(message = "Trường này không được để trống")
  private String email;

  @NotBlank(message = "Trường này không được để trống")
  private String password;

  public User mapToUser(){
    return User.builder().role(Role.USER).avatar(null).email(this.email).password(
        this.password).build();
  }


}