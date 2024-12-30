package com.loctran.service.entity.NoteCategory;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note-category")
@RequiredArgsConstructor
public class NoteCategoryController {
  private final NoteCategoryService noteCategoryService;

  @GetMapping
  public ResponseEntity<ResponseDto> getAllNoteCategories() {
    List<NoteCategory> noteCategories = noteCategoryService.getAllNoteCategories();
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy tất cả thông tin danh mục ghi chú thành công");
    responseDto.setStatus(200);
    responseDto.setData(noteCategories);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getNoteCategoryById(@PathVariable Long id) {
    NoteCategory noteCategory = noteCategoryService.getNoteCategoryById(id)
        .orElseThrow(() -> new ResourceNotFoundException("NoteCategory", "id", id.toString()));
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy thông tin danh mục ghi chú thành công");
    responseDto.setStatus(200);
    responseDto.setData(noteCategory);
    return ResponseEntity.ok(responseDto);
  }

  @PostMapping
  public ResponseEntity<ResponseDto> createNoteCategory(@RequestBody NoteCategory noteCategory) {
    NoteCategory newNoteCategory = noteCategoryService.createNoteCategory(noteCategory);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Tạo danh mục ghi chú thành công");
    responseDto.setStatus(200);
    responseDto.setData(newNoteCategory);
    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateNoteCategory(@PathVariable Long id, @RequestBody NoteCategory noteCategoryDetails) {
    NoteCategory updatedNoteCategory = noteCategoryService.updateNoteCategory(id, noteCategoryDetails);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Update thông tin danh mục ghi chú thành công");
    responseDto.setStatus(200);
    responseDto.setData(updatedNoteCategory);
    return ResponseEntity.ok(responseDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteNoteCategory(@PathVariable Long id) {
    noteCategoryService.deleteNoteCategory(id);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Xóa danh mục ghi chú thành công");
    responseDto.setStatus(200);
    return ResponseEntity.ok(responseDto);
  }
}