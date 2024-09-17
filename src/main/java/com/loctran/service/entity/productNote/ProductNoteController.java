package com.loctran.service.entity.productNote;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.productNote.dto.CreateProductNoteDto;
import com.loctran.service.entity.productNote.dto.UpdateProductNoteDto;
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

@RestController
@RequestMapping("notes")
@RequiredArgsConstructor
public class ProductNoteController {
    private final ProductNoteService productNoteService;

    @GetMapping("")
    public ResponseEntity<ResponseDto> getAllProductNotes() {
      List<ProductNote> productNotes = productNoteService.findAll();
      ResponseDto responseDto = ResponseDto.builder().build();
      responseDto.setMessage("Lấy thông tin thành công");
      responseDto.setStatus(200);
      responseDto.setData(productNotes);
      return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getProductNote(@PathVariable String id) {
      ProductNote productNotes = productNoteService.findById(Long.parseLong(id));
      ResponseDto responseDto = ResponseDto.builder().build();
      responseDto.setMessage("Lấy thông tin thành công");
      responseDto.setStatus(200);
      responseDto.setData(productNotes);
      return ResponseEntity.ok(responseDto);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> createProductNote(@RequestBody CreateProductNoteDto dto) {
      ProductNote productNote = productNoteService.save(dto);
      ResponseDto responseDto = ResponseDto.builder().build();
      responseDto.setMessage("Thêm nốt hương thành công");
      responseDto.setStatus(200);
      responseDto.setData(productNote);
      return ResponseEntity.ok(responseDto);
    }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateProductNote(@PathVariable("id") String id, @RequestBody UpdateProductNoteDto dto) {
    ProductNote productNote = productNoteService.update(Long.parseLong(id),dto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Update nốt hương thành công");
    responseDto.setStatus(200);
    responseDto.setData(productNote);
    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/notes/{id}/product/{productId}/add")
  public ResponseEntity<ResponseDto> addProductNoteToProduct(@PathVariable("id")String noteId,@PathVariable("productId")String productId) {
    ProductNote productNote = productNoteService.addNoteToProduct(Long.parseLong(noteId),Long.parseLong(productId));
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Thêm nốt hương vào sản phẩm thành công");
    responseDto.setStatus(200);
    responseDto.setData(productNote);
    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/notes/{id}/product/{productId}/remove")
  public ResponseEntity<ResponseDto> removeProductNoteFromProduct(@PathVariable("id")String noteId,@PathVariable("productId")String productId) {
    ProductNote productNote = productNoteService.removeNoteFromProduct(Long.parseLong(noteId),Long.parseLong(productId));
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Xóa nốt hương khỏi sản phẩm thành công");
    responseDto.setStatus(200);
    responseDto.setData(productNote);
    return ResponseEntity.ok(responseDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteProductNote(@PathVariable("id") String id) {
    ProductNote productNote = productNoteService.deleteProductNote(Long.parseLong(id));
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Xóa nốt hương thành công");
    responseDto.setStatus(200);
    responseDto.setData(productNote);
    return ResponseEntity.ok(responseDto);
  }
}
