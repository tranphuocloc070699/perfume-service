package com.loctran.service.entity.brand;

import com.loctran.service.common.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    return ResponseEntity.ok(ResponseDto.getDataSuccess(brands));
  }

  /**
   * Retrieves a brand by its ID.
   */
  @Operation(summary = "Get a brand by ID", description = "Retrieves a brand by its unique identifier")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getBrand(@PathVariable String id) {
    Brand brand = brandService.findById(Long.parseLong(id));
    return ResponseEntity.ok(ResponseDto.getDataSuccess(brand));
  }

  /**
   * Creates a new brand.
   */
  @Operation(summary = "Create a new brand", description = "Creates a new brand entry in the database")
  @PostMapping("")
  public ResponseEntity<ResponseDto> createBrand(@RequestBody UpsaveBrandDto dto) {
    Brand brand = brandService.create(dto);
    return ResponseEntity.ok(ResponseDto.createDataSuccess(brand));
  }

  /**
   * Updates an existing brand.
   */
  @Operation(summary = "Update a brand", description = "Updates an existing brand entry")
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateBrand(@PathVariable("id") String id, @RequestBody UpsaveBrandDto dto) {
    Brand brand = brandService.updateById(Long.parseLong(id), dto);
    return ResponseEntity.ok(ResponseDto.updateDataSuccess(brand));
  }

  /**
   * Deletes a brand by its ID.
   */
  @Operation(summary = "Delete a brand", description = "Deletes a brand by its unique identifier")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteBrand(@PathVariable("id") String id) {
    Brand brand = brandService.deleteById(Long.parseLong(id));
    return ResponseEntity.ok(ResponseDto.deleteDataSuccess(brand));
  }
}
