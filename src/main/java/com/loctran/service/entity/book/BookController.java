package com.loctran.service.entity.book;


import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.book.dto.BookDto;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.spi.Resolution;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API for managing books.
 * Provides endpoints for retrieving, creating, updating, and deleting books.
 */
@Tag(name = "Book", description = "APIs for managing books")
@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  /**
   * Retrieves all books.
   */
  @Operation(summary = "Get all books", description = "Retrieves all available books")
  @GetMapping("")
  public ResponseEntity<ResponseDto> getAll() {
    List<Book> books = bookService.getAll();
    return ResponseEntity.ok(ResponseDto.getDataSuccess(books));
  }

  /**
   * Retrieves a book by its ID.
   */
  @Operation(summary = "Get a book by ID", description = "Retrieves a book by its unique identifier")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getById(@PathVariable Long id) {
    Book book = bookService.findById(id);
    return ResponseEntity.ok(ResponseDto.getDataSuccess(book));
  }

  /**
   * Creates a new book.
   */
  @Operation(summary = "Create a new book", description = "Creates a new book entry in the database")
  @PostMapping("")
  public ResponseEntity<ResponseDto> create(@RequestBody BookDto dto) {
    Book book = bookService.create(dto);
    return ResponseEntity.ok(ResponseDto.createDataSuccess(book));
  }

  /**
   * Updates an existing book.
   */
  @Operation(summary = "Update a book", description = "Updates an existing book entry")
  @PutMapping("{id}")
  public ResponseEntity<ResponseDto> update(@PathVariable("id") Long bookId, @RequestBody BookDto dto) {
    Book book = bookService.updateById(bookId, dto);
    return ResponseEntity.ok(ResponseDto.updateDataSuccess(book));
  }

  /**
   * Deletes a book by its ID.
   */
  @Operation(summary = "Delete a book", description = "Deletes a book by its unique identifier")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long bookId) {
    Book book = bookService.deleteById(bookId);
    return ResponseEntity.ok(ResponseDto.deleteDataSuccess(book));
  }
}
