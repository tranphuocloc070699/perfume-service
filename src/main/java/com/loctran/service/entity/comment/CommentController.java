package com.loctran.service.entity.comment;

import com.loctran.service.common.CommonService;
import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.brand.dto.CreateBrandDto;
import com.loctran.service.entity.brand.dto.UpdateBrandDto;
import com.loctran.service.entity.comment.dto.CreateCommentDto;
import com.loctran.service.entity.comment.dto.UpdateCommentDto;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {
  private final CommentService commentService;
  private final CommonService commonService;

  @GetMapping("")
  public ResponseEntity<ResponseDto> getAllComment(HttpServletRequest request) {

    Long userId = commonService.getUserId(request);
    List<Comment> comments = commentService.findAll(userId);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy thông tin thành công");
    responseDto.setStatus(200);
    responseDto.setData(comments);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getComment(@PathVariable Long id) {
    Comment comment =  commentService.findById(id);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy thông tin thành công");
    responseDto.setStatus(200);
    responseDto.setData(comment);
    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("/product/{productId}")
  public ResponseEntity<ResponseDto> createProductComment(HttpServletRequest request,@PathVariable("productId")Long productId,@RequestBody CreateCommentDto createCommentDto) {
    Long userId = commonService.getUserId(request);
    Comment comment = commentService.saveProductComment(userId, productId, createCommentDto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Tạo bình luận thành công");
    responseDto.setStatus(200);
    responseDto.setData(comment);
    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("/post/{postId}")
  public ResponseEntity<ResponseDto> createPostComment(HttpServletRequest request,@PathVariable("postId")Long postId,@RequestBody CreateCommentDto createCommentDto) {
    Long userId = commonService.getUserId(request);
    Comment comment = commentService.savePostComment(userId, postId, createCommentDto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Tạo bình luận thành công");
    responseDto.setStatus(200);
    responseDto.setData(comment);
    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("/product-compare/{productCompareId}")
  public ResponseEntity<ResponseDto> createProductCompareComment(HttpServletRequest request,@PathVariable("productCompareId")Long productCompareId,@RequestBody CreateCommentDto createCommentDto) {
    Long userId = commonService.getUserId(request);
    Comment comment = commentService.saveProductCompareComment(userId, productCompareId, createCommentDto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Tạo bình luận thành công");
    responseDto.setStatus(200);
    responseDto.setData(comment);
    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("/answer/{answerId}")
  public ResponseEntity<ResponseDto> createAnswerComment(HttpServletRequest request,@PathVariable("answerId")Long answerId,@RequestBody CreateCommentDto createCommentDto) {
    Long userId = commonService.getUserId(request);
    Comment comment = commentService.saveAnswerComment(userId, answerId, createCommentDto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Tạo bình luận thành công");
    responseDto.setStatus(200);
    responseDto.setData(comment);
    return ResponseEntity.ok(responseDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> update(@PathVariable("id") Long id, @RequestBody UpdateCommentDto dto) {
    Comment comment = commentService.update(id,dto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Update bình luận thành công");
    responseDto.setStatus(200);
    responseDto.setData(comment);
    return ResponseEntity.ok(responseDto);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long id) {
    Comment comment = commentService.delete(id);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Xóa bình luận thành công");
    responseDto.setStatus(200);
    responseDto.setData(comment);
    return ResponseEntity.ok(responseDto);
  }

}
