package com.loctran.service.common;

import com.loctran.service.utils.MessageUtil.ResponseMessage;
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

  public static ResponseDto of(int status, String message, Object data) {
    return ResponseDto.builder()
        .status(status)
        .message(message)
        .data(data)
        .build();
  }

  public static ResponseDto getDataSuccess( Object data) {
    return ResponseDto.builder()
        .status(200)
        .message(ResponseMessage.GET_DATA_SUCCESS)
        .data(data)
        .build();
  }

  public static ResponseDto createDataSuccess( Object data) {
    return ResponseDto.builder()
        .status(200)
        .message(ResponseMessage.CREATE_DATA_SUCCESS)
        .data(data)
        .build();
  }

  public static ResponseDto updateDataSuccess( Object data) {
    return ResponseDto.builder()
        .status(200)
        .message(ResponseMessage.UPDATE_DATA_SUCCESS)
        .data(data)
        .build();
  }

  public static ResponseDto deleteDataSuccess( Object data) {
    return ResponseDto.builder()
        .status(200)
        .message(ResponseMessage.DELETE_DATA_SUCCESS)
        .data(data)
        .build();
  }
}
