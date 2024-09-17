package com.loctran.service.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
    super(String.format("%s không tìm thấy với thông tin %s : '%s'", resourceName, fieldName,
        fieldValue));
  }

}