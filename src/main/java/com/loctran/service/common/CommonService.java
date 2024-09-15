package com.loctran.service.common;

import com.loctran.service.utils.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CommonService {
  public void validate(BindingResult bindingResult) throws BindException {
    if(bindingResult.hasErrors()){
      throw new BindException(bindingResult);
    }
  }

  public Boolean uploadFile(String uploadDir, String fileName,
                            MultipartFile multipartFile){
    uploadDir = "/assets/images/" + uploadDir;
      try {
          FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
          return true;
      } catch (IOException e) {

          throw new RuntimeException(e);
      }
  }
}
