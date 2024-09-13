package com.loctran.service.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseDto {
  private Integer status;
  private Object data;
  private Object errors;
  private String message;
  private String path;
}
