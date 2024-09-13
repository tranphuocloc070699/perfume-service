package com.loctran.service.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JWTResponseDto {
  private String accessToken;
  private Object data;
}
