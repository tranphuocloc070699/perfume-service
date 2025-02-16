package com.loctran.service.entity.color;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
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
    List<Color> colors = colorService.getAll();
    return ResponseEntity.ok(ResponseDto.getDataSuccess(colors));
  }

  /**
   * Retrieves a color by its ID.
   */
  @Operation(summary = "Get a color by ID", description = "Retrieves a color by its unique identifier")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getColorById(@PathVariable Long id) {
    Color color = colorService.getById(id);
    return ResponseEntity.ok(ResponseDto.getDataSuccess(color));
  }

  /**
   * Creates a new color.
   */
  @Operation(summary = "Create a new color", description = "Creates a new color entry in the database")
  @PostMapping
  public ResponseEntity<ResponseDto> createColor(@RequestBody Color color) {
    Color newColor = colorService.create(color);

    return ResponseEntity.ok(ResponseDto.createDataSuccess(newColor));
  }

  /**
   * Updates an existing color.
   */
  @Operation(summary = "Update a color", description = "Updates an existing color entry")
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateColor(@PathVariable Long id, @RequestBody Color colorDetails) {
    Color updatedColor = colorService.updateById(id, colorDetails);
    return ResponseEntity.ok(ResponseDto.updateDataSuccess(updatedColor));
  }

  /**
   * Deletes a color by its ID.
   */
  @Operation(summary = "Delete a color", description = "Deletes a color by its unique identifier")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteColor(@PathVariable Long id) {
    colorService.deleteById(id);
    ResponseDto responseDto = ResponseDto.builder()
        .message(ResponseMessage.DELETE_DATA_SUCCESS)
        .status(200)
        .build();
    return ResponseEntity.ok(responseDto);
  }
}