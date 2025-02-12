package com.loctran.service.entity.book;


import com.loctran.service.common.CommonService;
import com.loctran.service.common.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy thông tin thành công");
    responseDto.setStatus(200);
    responseDto.setData(books);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Retrieves a book by its ID.
   */
  @Operation(summary = "Get a book by ID", description = "Retrieves a book by its unique identifier")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getById(@PathVariable Long id) {
    Book book = bookService.findById(id);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy thông tin thành công");
    responseDto.setStatus(200);
    responseDto.setData(book);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Creates a new book.
   */
  @Operation(summary = "Create a new book", description = "Creates a new book entry in the database")
  @PostMapping("")
  public ResponseEntity<ResponseDto> create(@RequestBody BookDto dto) {
    Book book = bookService.create(dto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Tạo sách thành công");
    responseDto.setStatus(200);
    responseDto.setData(book);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Updates an existing book.
   */
  @Operation(summary = "Update a book", description = "Updates an existing book entry")
  @PutMapping("{id}")
  public ResponseEntity<ResponseDto> update(@PathVariable("id") Long bookId, @RequestBody BookDto dto) {
    Book book = bookService.update(bookId, dto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Chỉnh sửa sách thành công");
    responseDto.setStatus(200);
    responseDto.setData(book);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Deletes a book by its ID.
   */
  @Operation(summary = "Delete a book", description = "Deletes a book by its unique identifier")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long bookId) {
    Book book = bookService.delete(bookId);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Xóa sách thành công");
    responseDto.setStatus(200);
    responseDto.setData(book);
    return ResponseEntity.ok(responseDto);
  }
}
