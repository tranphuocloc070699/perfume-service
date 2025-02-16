package com.loctran.service.entity.noteCategory;

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
    List<NoteCategory> noteCategories = noteCategoryService.getAll();
    return ResponseEntity.ok(ResponseDto.getDataSuccess(noteCategories));
  }

  /**
   * Retrieves a note category by its ID.
   */
  @Operation(summary = "Get a note category by ID", description = "Retrieves a note category by its ID")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getNoteCategoryById(@PathVariable Long id) {
    NoteCategory noteCategory = noteCategoryService.getById(id);

    return ResponseEntity.ok(ResponseDto.getDataSuccess(noteCategory));
  }

  /**
   * Creates a new note category.
   */
  @Operation(summary = "Create a note category", description = "Creates a new note category")
  @PostMapping
  public ResponseEntity<ResponseDto> createNoteCategory(
      @RequestBody UpsaveNoteCategoryDto noteCategory) {
    NoteCategory newNoteCategory = noteCategoryService.create(noteCategory);
    return ResponseEntity.ok(ResponseDto.createDataSuccess(newNoteCategory));
  }

  /**
   * Updates an existing note category.
   */
  @Operation(summary = "Update a note category", description = "Updates an existing note category")
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateNoteCategory(@PathVariable Long id,
      @RequestBody UpsaveNoteCategoryDto dto) {
    NoteCategory updatedNoteCategory = noteCategoryService.updateById(id, dto);
    return ResponseEntity.ok(ResponseDto.updateDataSuccess(updatedNoteCategory));
  }

  /**
   * Deletes a note category by its ID.
   */
  @Operation(summary = "Delete a note category", description = "Deletes a note category by its ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteNoteCategory(@PathVariable Long id) {
    NoteCategory noteCategory = noteCategoryService.getById(id);
    noteCategoryService.deleteById(id);
    return ResponseEntity.ok(ResponseDto.deleteDataSuccess(noteCategory));
  }
}
