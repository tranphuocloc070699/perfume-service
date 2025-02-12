package com.loctran.service.entity.post;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.post.dto.UpsavePostDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API for managing posts.
 * Provides endpoints for retrieving, creating, updating, voting, and deleting posts.
 */
@Tag(name = "Post", description = "APIs for managing posts")
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
private final PostService postService;

  /**
   * Retrieves all posts with pagination and optional filters.
   */
  @Operation(summary = "Get all posts", description = "Retrieves all posts with pagination and optional filters")
  @GetMapping("")
  public ResponseEntity<ResponseDto> getAllPost(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "id") String sortBy,
      @RequestParam(defaultValue = "asc") String sortDir,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) Boolean isPinned) {
    Page<Post> posts;

    if (title != null && !title.isEmpty()) {
      posts = postService.getPostsByTitle(title, page, size, sortBy, sortDir);
    } else if (isPinned != null) {
      posts = postService.getPostsByIsPinned(isPinned, page, size, sortBy, sortDir);
    } else {
      posts = postService.getAllPost(page, size, sortBy, sortDir);
    }

    ResponseDto responseDto = ResponseDto.builder()
        .message("Lấy tất cả thông tin bài viết thành công")
        .status(200)
        .data(posts)
        .build();
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Retrieves all post IDs.
   */
  @Operation(summary = "Get all post IDs", description = "Retrieves all post IDs")
  @GetMapping("/id")
  public ResponseEntity<ResponseDto> getAllPostId() {
    List<Long> ids = postService.getAllPostId();
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy IDs thành công");
    responseDto.setStatus(200);
    responseDto.setData(ids);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Retrieves a post by its ID.
   */
  @Operation(summary = "Get a post by ID", description = "Retrieves a post by its ID")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getPostById(@PathVariable("id") Long id) {
    Post post = postService.getPostById(id);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy thông tin bài viết thành công");
    responseDto.setStatus(200);
    responseDto.setData(post);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Creates a new post.
   */
  @Operation(summary = "Create a post", description = "Creates a new post")
  @PostMapping("")
  public ResponseEntity<ResponseDto> createPost(HttpServletRequest request, @RequestBody UpsavePostDto dto) {
    Long userId = (Long) request.getAttribute("userId");
    Post post = postService.createPost(userId, dto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Tạo bài viết thành công");
    responseDto.setStatus(200);
    responseDto.setData(post);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Updates an existing post.
   */
  @Operation(summary = "Update a post", description = "Updates an existing post")
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updatePost(@PathVariable("id") Long id, @RequestBody UpsavePostDto dto) {
    Post post = postService.updatePost(id, dto);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Chỉnh sửa bài viết thành công");
    responseDto.setStatus(200);
    responseDto.setData(post);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Toggles the vote status for a post.
   */
  @Operation(summary = "Toggle vote for a post", description = "Toggles the vote status for a post")
  @PutMapping("/{id}/vote")
  public ResponseEntity<ResponseDto> toggleVote(HttpServletRequest request, @PathVariable("id") Long postId) {
    Long userId = (Long) request.getAttribute("userId");
    Post post = postService.toggleVote(userId, postId);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Toggle vote thành công");
    responseDto.setStatus(200);
    responseDto.setData(post);
    return ResponseEntity.ok(responseDto);
  }

  /**
   * Deletes a post by its ID.
   */
  @Operation(summary = "Delete a post", description = "Deletes a post by its ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deletePost(@PathVariable("id") Long id) {
    Post post = postService.deletePost(id);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Xóa bài viết thành công");
    responseDto.setStatus(200);
    responseDto.setData(post);
    return ResponseEntity.ok(responseDto);
  }
}
