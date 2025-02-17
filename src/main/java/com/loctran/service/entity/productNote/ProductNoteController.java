package com.loctran.service.entity.productNote;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.productNote.dto.CreateProductNoteDto;
import com.loctran.service.entity.productNote.dto.UpdateProductNoteDto;
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
 * API for managing product notes.
 * Provides endpoints for retrieving, creating, updating, and deleting product notes.
 */
@Tag(name = "Product Note", description = "APIs for managing product notes")
@RestController
@RequestMapping("notes")
@RequiredArgsConstructor
public class ProductNoteController {
    private final ProductNoteService productNoteService;

  /**
   * Retrieves all product notes.
   */
  @Operation(summary = "Get all product notes", description = "Retrieves all product notes")
  @GetMapping("")
  public ResponseEntity<ResponseDto> getAllProductNotes() {
    List<ProductNote> productNotes = productNoteService.findAll();
    return ResponseEntity.ok(ResponseDto.getDataSuccess(productNotes));
  }

  /**
   * Retrieves a product note by its ID.
   */
  @Operation(summary = "Get a product note by ID", description = "Retrieves a product note by its ID")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getProductNote(@PathVariable String id) {
    ProductNote productNotes = productNoteService.findById(Long.parseLong(id));
    return ResponseEntity.ok(ResponseDto.getDataSuccess(productNotes));
  }

  /**
   * Creates a new product note.
   */
  @Operation(summary = "Create a product note", description = "Creates a new product note")
  @PostMapping("")
  public ResponseEntity<ResponseDto> createProductNote(@RequestBody CreateProductNoteDto dto) {
    ProductNote productNote = productNoteService.save(dto);
    return ResponseEntity.ok(ResponseDto.createDataSuccess(productNote));
  }

  /**
   * Updates an existing product note.
   */
  @Operation(summary = "Update a product note", description = "Updates an existing product note")
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateProductNote(@PathVariable("id") String id, @RequestBody UpdateProductNoteDto dto) {
    ProductNote productNote = productNoteService.update(Long.parseLong(id), dto);
    return ResponseEntity.ok(ResponseDto.updateDataSuccess(productNote));
  }

  /**
   * Adds a product note to a product.
   */
  @Operation(summary = "Add a product note to a product", description = "Adds a product note to a product")
  @PutMapping("/notes/{id}/product/{productId}/add")
  public ResponseEntity<ResponseDto> addProductNoteToProduct(@PathVariable("id") String noteId, @PathVariable("productId") String productId) {
    ProductNote productNote = productNoteService.addNoteToProduct(Long.parseLong(noteId), Long.parseLong(productId));
    return ResponseEntity.ok(ResponseDto.updateDataSuccess(productNote));
  }

  /**
   * Removes a product note from a product.
   */
  @Operation(summary = "Remove a product note from a product", description = "Removes a product note from a product")
  @PutMapping("/notes/{id}/product/{productId}/remove")
  public ResponseEntity<ResponseDto> removeProductNoteFromProduct(@PathVariable("id") String noteId, @PathVariable("productId") String productId) {
    ProductNote productNote = productNoteService.removeNoteFromProduct(Long.parseLong(noteId), Long.parseLong(productId));
    return ResponseEntity.ok(ResponseDto.updateDataSuccess(productNote));
  }

  /**
   * Deletes a product note by its ID.
   */
  @Operation(summary = "Delete a product note", description = "Deletes a product note by its ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteProductNote(@PathVariable("id") String id) {
    ProductNote productNote = productNoteService.deleteProductNote(Long.parseLong(id));
    return ResponseEntity.ok(ResponseDto.deleteDataSuccess(productNote));
  }
}
