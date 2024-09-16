package com.loctran.service.admin;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.media.MediaType;
import com.loctran.service.media.dto.AddMediaToProductDto;
import com.loctran.service.product.Product;
import com.loctran.service.product.ProductRepository;
import com.loctran.service.product.ProductService;
import com.loctran.service.product.dto.CreateProductDto;
import com.loctran.service.user.dto.JWTResponseDto;
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

    @PostMapping("/upload")
    public String upload(@RequestParam("image") MultipartFile multipartFile, @RequestParam("id") String id){
        String filename = multipartFile.getOriginalFilename();


        try {
            FileUploadUtil.saveFile(id,filename,multipartFile);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<ResponseDto> createProduct(@RequestBody CreateProductDto dto){
        Product product = productService.createProduct(dto);

        ResponseDto responseDto = ResponseDto.builder().path("/admin/product").build();

        responseDto.setMessage("Tạo sản phẩm thành công");
        responseDto.setStatus(200);
        responseDto.setData(product);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/product/{id}/gallery")
    public ResponseEntity<ResponseDto> addGalleryToProduct(@PathVariable("id") String id,@RequestParam("image") MultipartFile multipartFile){
      try {
          String fileDir = "product-" + id;
        FileUploadUtil.saveFile(fileDir,multipartFile.getOriginalFilename(),multipartFile);

      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      return null;
    }
}
