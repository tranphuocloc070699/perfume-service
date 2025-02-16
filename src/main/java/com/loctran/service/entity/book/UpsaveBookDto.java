package com.loctran.service.entity.book;

import com.loctran.service.utils.MessageUtil.ResponseMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsaveBookDto {
  @NotBlank(message = ResponseMessage.VALIDATE_NOT_BLANK)
  private String name;

  @NotBlank(message = ResponseMessage.VALIDATE_NOT_BLANK)
  private String description;

  @NotBlank(message = ResponseMessage.VALIDATE_NOT_BLANK)
  private String link;

  @NotBlank(message = ResponseMessage.VALIDATE_NOT_BLANK)
  private String thumbnail;
}