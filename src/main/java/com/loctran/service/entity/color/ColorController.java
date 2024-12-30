package com.loctran.service.entity.color;

import com.loctran.service.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colors")
@RequiredArgsConstructor
public class ColorController {
  private final ColorService colorService;

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