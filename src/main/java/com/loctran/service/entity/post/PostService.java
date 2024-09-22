package com.loctran.service.entity.post;


import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.media.MediaRepository;
import com.loctran.service.entity.post.dto.CreatePostDto;
import com.loctran.service.entity.post.dto.UpdatePostDto;
import com.loctran.service.entity.product.dto.ListProductDto;
import com.loctran.service.entity.user.User;
import com.loctran.service.entity.user.UserRepository;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
  private final MediaRepository mediaRepository;

  public Page<Post> getAllPost(int page, int size,String sortBy,String sortDir) {
    Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
    Pageable pageable = PageRequest.of(page, size,sort);
    return postRepository.findAll(pageable);
  }

  public Post getPostById(Long id) {
    return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id.toString()));
  }

  public Post createPost(Long userId, CreatePostDto dto) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",
        userId.toString()));
    Post post = dto.mapToPost();
    Media media = Media.builder().type(dto.getThumbnail().getType()).path(dto.getThumbnail().getPath()).build();
    post.setThumbnail(media);
    post.setUser(user);
    return postRepository.save(post);
  }


  public Post updatePost(Long id, UpdatePostDto dto) {
      Post post = getPostById(id);


    post.setTitle(dto.getTitle());
    post.setExcerpt(dto.getExcerpt());
    post.setContent(dto.getContent());
    if(dto.getThumbnail().getId()==null && !dto.getThumbnail().getPath().equals(post.getThumbnail().getPath())){
      Media thumbnail = Media.builder().path(dto.getThumbnail().getPath()).type(dto.getThumbnail()
          .getType()).build();
      thumbnail.setPost(post);
      post.setThumbnail(mediaRepository.save(thumbnail));
    }else{
      post.getThumbnail().setPath(dto.getThumbnail().getPath());
    }

    return postRepository.save(post);
  }

  public Post deletePost(Long id) {
    Post post = getPostById(id);
    postRepository.delete(post);
    return post;
  }

}
