package com.loctran.service.entity.post.dto;

import com.loctran.service.entity.media.dto.MediaDto;
import com.loctran.service.entity.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostDto {
  private String title;
  private String excerpt;
  private String content;
  private Boolean isPinned;
  private MediaDto thumbnail;

  public Post mapToPost(){
    return Post.builder().title(this.title).excerpt(this.excerpt).content(this.content).isPinned(this.isPinned).build();
  }
}
