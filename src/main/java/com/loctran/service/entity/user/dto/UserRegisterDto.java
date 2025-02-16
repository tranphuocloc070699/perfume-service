package com.loctran.service.entity.user.dto;

import com.loctran.service.entity.user.Role;
import com.loctran.service.entity.user.User;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
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
  @NotBlank(message = ResponseMessage.VALIDATE_NOT_BLANK)
  @Email(message = ResponseMessage.VALIDATE_EMAIL_INVALID)
  private String email;

  @NotBlank(message = ResponseMessage.VALIDATE_NOT_BLANK)
  private String name;

  @NotBlank(message = ResponseMessage.VALIDATE_NOT_BLANK)
  @Size(min = 6, message = ResponseMessage.VALIDATE_PASSWORD_INVALID)
  private String password;

  public User mapToUser(){
    return User.builder().role(Role.USER).avatar(null).email(this.email).name(this.name).password(
        this.password).build();
  }
}