package com.loctran.service.entity.user.dto;

import com.loctran.service.entity.user.Role;
import com.loctran.service.entity.user.User;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
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
  @NotBlank(message = ResponseMessage.VALIDATE_NOT_BLANK)
  private String email;

  @NotBlank(message = ResponseMessage.VALIDATE_NOT_BLANK)
  private String password;

}