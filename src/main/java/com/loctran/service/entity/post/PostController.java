package com.loctran.service.entity.post;

import com.loctran.service.common.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  @GetMapping
  public ResponseEntity<ResponseDto> getAllPost(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "id") String sortBy,
      @RequestParam(defaultValue = "asc") String sortDir,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) Boolean isPinned) {
    Page<Post> posts = (title != null && !title.isEmpty())
        ? postService.getPostsByTitle(title, page, size, sortBy, sortDir)
        : (isPinned != null)
            ? postService.getPostsByIsPinned(isPinned, page, size, sortBy, sortDir)
            : postService.getAllPost(page, size, sortBy, sortDir);
    return ResponseEntity.ok(ResponseDto.getDataSuccess(posts));
  }

  /**
   * Retrieves all post IDs.
   */
  @Operation(summary = "Get all post IDs", description = "Retrieves all post IDs")
  @GetMapping("/id")
  public ResponseEntity<ResponseDto> getAllPostId() {
    List<Long> ids = postService.getAllPostId();
    return ResponseEntity.ok(ResponseDto.getDataSuccess(ids));
  }

  /**
   * Retrieves a post by its ID.
   */
  @Operation(summary = "Get a post by ID", description = "Retrieves a post by its ID")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getPostById(@PathVariable Long id) {
    Post post = postService.getPostById(id);
    return ResponseEntity.ok(ResponseDto.getDataSuccess(post));
  }

  /**
   * Creates a new post.
   */
  @Operation(summary = "Create a post", description = "Creates a new post")
  @PostMapping
  public ResponseEntity<ResponseDto> createPost(HttpServletRequest request, @RequestBody UpsavePostDto dto) {
    Long userId = (Long) request.getAttribute("userId");
    Post post = postService.create(userId, dto);
    return ResponseEntity.ok(ResponseDto.createDataSuccess(post));
  }

  /**
   * Updates an existing post.
   */
  @Operation(summary = "Update a post", description = "Updates an existing post")
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updatePost(@PathVariable Long id, @RequestBody UpsavePostDto dto) {
    Post post = postService.updateById(id, dto);
    return ResponseEntity.ok(ResponseDto.updateDataSuccess(post));
  }

  /**
   * Toggles the vote status for a post.
   */
  @Operation(summary = "Toggle vote for a post", description = "Toggles the vote status for a post")
  @PutMapping("/{id}/vote")
  public ResponseEntity<ResponseDto> toggleVote(HttpServletRequest request, @PathVariable Long postId) {
    Long userId = (Long) request.getAttribute("userId");
    Post post = postService.toggleVote(userId, postId);
    return ResponseEntity.ok(ResponseDto.updateDataSuccess(post));
  }

  /**
   * Deletes a post by its ID.
   */
  @Operation(summary = "Delete a post", description = "Deletes a post by its ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deletePost(@PathVariable Long id) {
    Post post = postService.deleteById(id);
    return ResponseEntity.ok(ResponseDto.deleteDataSuccess(post));
  }
}
