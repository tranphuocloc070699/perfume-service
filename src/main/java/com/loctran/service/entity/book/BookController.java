package com.loctran.service.entity.book;


import com.loctran.service.common.CommonService;
import com.loctran.service.common.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;


  @GetMapping("")
  public ResponseEntity<ResponseDto> getAll() {

    List<Book> books = bookService.getAll();
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy thông tin thành công");
    responseDto.setStatus(200);
    responseDto.setData(books);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getById(@PathVariable Long id) {
    Book book =  bookService.findById(id);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy thông tin thành công");
    responseDto.setStatus(200);
    responseDto.setData(book);
    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("")
  public ResponseEntity<ResponseDto> create(@RequestBody BookDto dto) {
    Book book = bookService.create(dto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Tạo sách thành công");
    responseDto.setStatus(200);
    responseDto.setData(book);
    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("{id}")
  public ResponseEntity<ResponseDto> update(@PathVariable("id")Long bookId,@RequestBody BookDto dto) {

    Book book = bookService.update(bookId,dto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Chỉnh sửa câu hỏi thành công");
    responseDto.setStatus(200);
    responseDto.setData(book);
    return ResponseEntity.ok(responseDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long bookId) {
    Book book = bookService.delete(bookId);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Xóa câu  thành công");
    responseDto.setStatus(200);
    responseDto.setData(book);
    return ResponseEntity.ok(responseDto);
  }
}
