package com.loctran.service.exception;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.exception.custom.AlreadyExistException;
import com.loctran.service.exception.custom.ConflictException;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)  // Nếu validate fail thì trả về 422
  public  ResponseEntity<ResponseDto> handleBindException(BindException e) {
    Map<String, String> validationErrors = new HashMap<>();
    List<ObjectError> errorList = e.getBindingResult().getAllErrors();

    errorList.forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String validationMsg = error.getDefaultMessage();
      validationErrors.put(fieldName, validationMsg);
    });
    ResponseDto responseDto = ResponseDto.builder()
        .path(null)
        .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
        .message("Validate failure")
        .errors(validationErrors)
        .data(null)
        .build();
    return new ResponseEntity<>(responseDto, HttpStatus.UNPROCESSABLE_ENTITY);

  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseDto> handleGlobalException(Exception exception,
      WebRequest webRequest) {
    ResponseDto responseDto = ResponseDto.builder()
        .path(webRequest.getContextPath())
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("Process failure")
        .errors(exception.getMessage())
        .data(null)
        .build();
    return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ResponseDto> handleResourceNotFoundException(
      ResourceNotFoundException exception,
      WebRequest webRequest) {
    ResponseDto responseDto = ResponseDto.builder()
        .path(webRequest.getContextPath())
        .status(HttpStatus.NOT_FOUND.value())
        .message("Process failure")
        .errors(exception.getMessage())
        .data(null)
        .build();
    return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ResponseDto> handleConflictException(ConflictException exception,
      WebRequest webRequest) {
    ResponseDto responseDto = ResponseDto.builder()
        .path(webRequest.getContextPath())
        .status(HttpStatus.CONFLICT.value())
        .message("Process failure")
        .errors(exception.getMessage())
        .data(null)
        .build();
    return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(AlreadyExistException.class)
  public ResponseEntity<ResponseDto> handleAlreadyExistsException(
      AlreadyExistException exception,
      WebRequest webRequest) {
    ResponseDto responseDto = ResponseDto.builder()
        .path(webRequest.getContextPath())
        .status(HttpStatus.BAD_REQUEST.value())
        .message("Process failure")
        .errors(exception.getMessage())
        .data(null)
        .build();
    return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
  }

}