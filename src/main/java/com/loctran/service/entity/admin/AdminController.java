package com.loctran.service.entity.admin;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.product.ProductService;
import com.loctran.service.entity.product.dto.CreateProductDto;
import com.loctran.service.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final ProductService productService;

    @GetMapping("")
    public String hello() {
      return "hello from admin";
    }

//    @PostMapping("/upload")
//    public String upload(@RequestParam("image") MultipartFile multipartFile, @RequestParam("id") String id){
//        String filename = multipartFile.getOriginalFilename();
//        try {
//          return FileUploadUtil.saveFile(id,filename,multipartFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//

}
