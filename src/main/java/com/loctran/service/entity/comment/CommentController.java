package com.loctran.service.entity.comment;

import com.loctran.service.common.CommonService;
import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.brand.dto.CreateBrandDto;
import com.loctran.service.entity.brand.dto.UpdateBrandDto;
import com.loctran.service.entity.comment.dto.CreateCommentDto;
import com.loctran.service.entity.comment.dto.UpdateCommentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

/**
 * API for managing comments.
 * Provides endpoints for retrieving, creating, updating, and deleting comments.
 */
@Tag(name = "Comment", description = "APIs for managing comments")
@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {
  private final CommentService commentService;
  private final CommonService commonService;

  /**
   * Retrieves all comments for the authenticated user.
   */
  @Operation(summary = "Get all comments", description = "Retrieves all comments for the authenticated user")
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

  /**
   * Retrieves a comment by its ID.
   */
  @Operation(summary = "Get a comment by ID", description = "Retrieves a comment by its ID")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getComment(@PathVariable Long id) {
    Comment comment = commentService.findById(id);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy thông tin thành công");
    responseDto.setStatus(200);
    responseDto.setData(comment);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Creates a new comment for a product.
   */
  @Operation(summary = "Create a product comment", description = "Creates a new comment for a product")
  @PostMapping("/product/{productId}")
  public ResponseEntity<ResponseDto> createProductComment(HttpServletRequest request, @PathVariable("productId") Long productId, @RequestBody CreateCommentDto createCommentDto) {
    Long userId = commonService.getUserId(request);
    Comment comment = commentService.saveProductComment(userId, productId, createCommentDto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Tạo bình luận thành công");
    responseDto.setStatus(200);
    responseDto.setData(comment);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Updates an existing comment.
   */
  @Operation(summary = "Update a comment", description = "Updates an existing comment")
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> update(@PathVariable("id") Long id, @RequestBody UpdateCommentDto dto) {
    Comment comment = commentService.update(id, dto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Update bình luận thành công");
    responseDto.setStatus(200);
    responseDto.setData(comment);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Deletes a comment by its ID.
   */
  @Operation(summary = "Delete a comment", description = "Deletes a comment by its ID")
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
