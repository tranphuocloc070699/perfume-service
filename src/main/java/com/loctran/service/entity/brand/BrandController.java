package com.loctran.service.entity.brand;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.brand.dto.CreateBrandDto;
import com.loctran.service.entity.brand.dto.UpdateBrandDto;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("brand")
@RequiredArgsConstructor
public class BrandController {
private final BrandService brandService;
  @GetMapping("")
  public ResponseEntity<ResponseDto> getAllBrand() {
    List<Brand> brands = brandService.findAll();
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy thông tin thành công");
    responseDto.setStatus(200);
    responseDto.setData(brands);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getBrand(@PathVariable String id) {
    Brand brand = brandService.findById(Long.parseLong(id));
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy thông tin thành công");
    responseDto.setStatus(200);
    responseDto.setData(brand);
    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("")
  public ResponseEntity<ResponseDto> createBrand(@RequestBody CreateBrandDto dto) {
    Brand brand = brandService.save(dto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Thêm thương hiệu thành công");
    responseDto.setStatus(200);
    responseDto.setData(brand);
    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateBrand(@PathVariable("id") String id, @RequestBody UpdateBrandDto dto) {
    Brand brand = brandService.update(Long.parseLong(id),dto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Update thương hiệu thành công");
    responseDto.setStatus(200);
    responseDto.setData(brand);
    return ResponseEntity.ok(responseDto);
  }

//  @PutMapping("/notes/{id}/product/{productId}/add")
//  public ResponseEntity<ResponseDto> addProductNoteToProduct(@PathVariable("id")String noteId,@PathVariable("productId")String productId) {
//    ProductNote productNote = productNoteService.addNoteToProduct(Long.parseLong(noteId),Long.parseLong(productId));
//    ResponseDto responseDto = ResponseDto.builder().path("/notes").build();
//    responseDto.setMessage("Thêm nốt hương vào sản phẩm thành công");
//    responseDto.setStatus(200);
//    responseDto.setData(productNote);
//    return ResponseEntity.ok(responseDto);
//  }
//
//  @PutMapping("/notes/{id}/product/{productId}/remove")
//  public ResponseEntity<ResponseDto> removeProductNoteFromProduct(@PathVariable("id")String noteId,@PathVariable("productId")String productId) {
//    ProductNote productNote = productNoteService.removeNoteFromProduct(Long.parseLong(noteId),Long.parseLong(productId));
//    ResponseDto responseDto = ResponseDto.builder().path("/").build();
//    responseDto.setMessage("Xóa nốt hương khỏi sản phẩm thành công");
//    responseDto.setStatus(200);
//    responseDto.setData(productNote);
//    return ResponseEntity.ok(responseDto);
//  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteBrand(@PathVariable("id") String id) {
    Brand brand = brandService.deleteBrand(Long.parseLong(id));
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Xóa thương hiệu thành công");
    responseDto.setStatus(200);
    responseDto.setData(brand);
    return ResponseEntity.ok(responseDto);
  }
}
