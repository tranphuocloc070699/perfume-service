package com.loctran.service.entity.post;


import com.loctran.service.entity.post.dto.UpsavePostDto;
import com.loctran.service.entity.user.User;
import com.loctran.service.entity.user.UserRepository;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;


  public Page<Post> getAllPost(int page, int size, String sortBy, String sortDir) {
    Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
    Pageable pageable = PageRequest.of(page, size, sort);
    return postRepository.findAll(pageable);
  }

  public Page<Post> getPostsByIsPinned(Boolean isPinned, int page, int size, String sortBy, String sortDir) {
    Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
    Pageable pageable = PageRequest.of(page, size, sort);
    return postRepository.findAllByIsPinned(isPinned, pageable);
  }

  public List<Long> getAllPostId() {
    return postRepository.findAllIds();
  }

  public Page<Post> getPostsByTitle(String title, int page, int size, String sortBy, String sortDir) {
    Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
    Pageable pageable = PageRequest.of(page, size, sort);
    return postRepository.findByTitleContainingIgnoreCase(title, pageable);
  }

  public Post getPostById(Long id) {
    return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id.toString()));
  }

  public Post createPost(Long userId, UpsavePostDto dto) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",
        userId.toString()));
    Post post = dto.mapToPost();
    System.out.println("after map to Post");
    post.setThumbnail(dto.getThumbnail());
    post.setUser(user);
    System.out.println("after set user");
    return postRepository.save(post);
  }

  public Post updatePost(Long id, UpsavePostDto dto) {
      Post post = getPostById(id);
    post.setTitle(dto.getTitle());
    post.setExcerpt(dto.getExcerpt());
    post.setContent(dto.getContent());
    post.setIsPinned(dto.getIsPinned());
    post.setThumbnail(dto.getThumbnail());
    return postRepository.save(post);
  }

  public Post toggleVote(Long userId, Long postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));
    if (post.getVotes() == null) {
      post.setVotes(new ArrayList<>());
    }

    if (post.getVotes().contains(userId)) {
      post.getVotes().remove(userId);
    } else {
      post.getVotes().add(userId);
    }
    return postRepository.save(post);
  }

  public Post deletePost(Long id) {
    Post post = getPostById(id);
    postRepository.delete(post);
    return post;
  }

}
