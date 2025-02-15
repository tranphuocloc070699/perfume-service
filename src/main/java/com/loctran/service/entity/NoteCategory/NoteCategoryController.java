package com.loctran.service.entity.NoteCategory;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * API for managing note categories.
 * Provides endpoints for retrieving, creating, updating, and deleting note categories.
 */
@Tag(name = "Note Category", description = "APIs for managing note categories")
@RestController
@RequestMapping("/note-category")
@RequiredArgsConstructor
public class NoteCategoryController {
  private final NoteCategoryService noteCategoryService;

  /**
   * Retrieves all note categories.
   */
  @Operation(summary = "Get all note categories", description = "Retrieves all note categories")
  @GetMapping
  public ResponseEntity<ResponseDto> getAllNoteCategories() {
    List<NoteCategory> noteCategories = noteCategoryService.getAllNoteCategories();
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy tất cả thông tin danh mục ghi chú thành công");
    responseDto.setStatus(200);
    responseDto.setData(noteCategories);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Retrieves a note category by its ID.
   */
  @Operation(summary = "Get a note category by ID", description = "Retrieves a note category by its ID")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getNoteCategoryById(@PathVariable Long id) {
    NoteCategory noteCategory = noteCategoryService.getNoteCategoryById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy thông tin danh mục ghi chú thành công");
    responseDto.setStatus(200);
    responseDto.setData(noteCategory);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Creates a new note category.
   */
  @Operation(summary = "Create a note category", description = "Creates a new note category")
  @PostMapping
  public ResponseEntity<ResponseDto> createNoteCategory(@RequestBody NoteCategory noteCategory) {
    NoteCategory newNoteCategory = noteCategoryService.createNoteCategory(noteCategory);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Tạo danh mục ghi chú thành công");
    responseDto.setStatus(200);
    responseDto.setData(newNoteCategory);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Updates an existing note category.
   */
  @Operation(summary = "Update a note category", description = "Updates an existing note category")
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateNoteCategory(@PathVariable Long id, @RequestBody NoteCategory noteCategoryDetails) {
    NoteCategory updatedNoteCategory = noteCategoryService.updateNoteCategory(id, noteCategoryDetails);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Update thông tin danh mục ghi chú thành công");
    responseDto.setStatus(200);
    responseDto.setData(updatedNoteCategory);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Deletes a note category by its ID.
   */
  @Operation(summary = "Delete a note category", description = "Deletes a note category by its ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteNoteCategory(@PathVariable Long id) {
    noteCategoryService.deleteNoteCategory(id);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Xóa danh mục ghi chú thành công");
    responseDto.setStatus(200);
    return ResponseEntity.ok(responseDto);
  }
}