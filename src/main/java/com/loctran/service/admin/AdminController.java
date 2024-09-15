package com.loctran.service.admin;

import com.loctran.service.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("")
    public String hello() {
      return "hello from admin";
    }

    @PostMapping("/static/upload")
    public String upload(@RequestParam("image") MultipartFile multipartFile,@RequestParam("id") String id){
        String filename = multipartFile.getOriginalFilename();
        String uploadDir = "src/main/resources/upload/"+id;
        try {
            FileUploadUtil.saveFile(uploadDir,filename,multipartFile);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
