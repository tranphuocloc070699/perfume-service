package com.loctran.service.entity.brand;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.brand.dto.CreateBrandDto;
import com.loctran.service.entity.brand.dto.UpdateBrandDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

/**
 * API for managing brands.
 * Provides endpoints for retrieving, creating, updating, and deleting brands.
 */
@Tag(name = "Brand", description = "APIs for managing brands")
@RestController
@RequestMapping("brand")
@RequiredArgsConstructor
public class BrandController {
private final BrandService brandService;
  /**
   * Retrieves all brands.
   */
  @Operation(summary = "Get all brands", description = "Retrieves all available brands")
  @GetMapping("")
  public ResponseEntity<ResponseDto> getAllBrand() {
    List<Brand> brands = brandService.findAll();
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy thông tin thành công");
    responseDto.setStatus(200);
    responseDto.setData(brands);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Retrieves a brand by its ID.
   */
  @Operation(summary = "Get a brand by ID", description = "Retrieves a brand by its unique identifier")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getBrand(@PathVariable String id) {
    Brand brand = brandService.findById(Long.parseLong(id));
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy thông tin thành công");
    responseDto.setStatus(200);
    responseDto.setData(brand);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Creates a new brand.
   */
  @Operation(summary = "Create a new brand", description = "Creates a new brand entry in the database")
  @PostMapping("")
  public ResponseEntity<ResponseDto> createBrand(@RequestBody CreateBrandDto dto) {
    Brand brand = brandService.save(dto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Thêm thương hiệu thành công");
    responseDto.setStatus(200);
    responseDto.setData(brand);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Updates an existing brand.
   */
  @Operation(summary = "Update a brand", description = "Updates an existing brand entry")
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateBrand(@PathVariable("id") String id, @RequestBody UpdateBrandDto dto) {
    Brand brand = brandService.update(Long.parseLong(id), dto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Update thương hiệu thành công");
    responseDto.setStatus(200);
    responseDto.setData(brand);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Deletes a brand by its ID.
   */
  @Operation(summary = "Delete a brand", description = "Deletes a brand by its unique identifier")
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
