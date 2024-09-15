package com.loctran.service.common;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

@Service
public class CommonService {
  public void validate(BindingResult bindingResult) throws BindException {
    if(bindingResult.hasErrors()){
      throw new BindException(bindingResult);
    }
  }
}
