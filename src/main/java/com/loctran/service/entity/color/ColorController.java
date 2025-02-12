package com.loctran.service.entity.color;

import com.loctran.service.common.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * API for managing colors.
 * Provides endpoints for retrieving, creating, updating, and deleting colors.
 */
@Tag(name = "Color", description = "APIs for managing colors")
@RestController
@RequestMapping("/colors")
@RequiredArgsConstructor
public class ColorController {
  private final ColorService colorService;


  /**
   * Retrieves all colors.
   */
  @Operation(summary = "Get all colors", description = "Retrieves all available colors")
  @GetMapping
  public ResponseEntity<ResponseDto> getAllColors() {
    List<Color> colors = colorService.getAllColors();
    ResponseDto responseDto = ResponseDto.builder()
        .message("Lấy tất cả thông tin màu sắc thành công")
        .status(200)
        .data(colors)
        .build();
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Retrieves a color by its ID.
   */
  @Operation(summary = "Get a color by ID", description = "Retrieves a color by its unique identifier")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getColorById(@PathVariable Long id) {
    Color color = colorService.getColorById(id);
    ResponseDto responseDto = ResponseDto.builder()
        .message("Lấy thông tin màu sắc thành công")
        .status(200)
        .data(color)
        .build();
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Creates a new color.
   */
  @Operation(summary = "Create a new color", description = "Creates a new color entry in the database")
  @PostMapping
  public ResponseEntity<ResponseDto> createColor(@RequestBody Color color) {
    Color newColor = colorService.createColor(color);
    ResponseDto responseDto = ResponseDto.builder()
        .message("Tạo màu sắc thành công")
        .status(200)
        .data(newColor)
        .build();
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Updates an existing color.
   */
  @Operation(summary = "Update a color", description = "Updates an existing color entry")
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateColor(@PathVariable Long id, @RequestBody Color colorDetails) {
    Color updatedColor = colorService.updateColor(id, colorDetails);
    ResponseDto responseDto = ResponseDto.builder()
        .message("Cập nhật màu sắc thành công")
        .status(200)
        .data(updatedColor)
        .build();
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Deletes a color by its ID.
   */
  @Operation(summary = "Delete a color", description = "Deletes a color by its unique identifier")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteColor(@PathVariable Long id) {
    colorService.deleteColor(id);
    ResponseDto responseDto = ResponseDto.builder()
        .message("Xóa màu sắc thành công")
        .status(200)
        .build();
    return ResponseEntity.ok(responseDto);
  }
}