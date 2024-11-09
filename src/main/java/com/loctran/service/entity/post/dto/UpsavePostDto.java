package com.loctran.service.entity.post.dto;

import com.loctran.service.entity.post.Post;
import com.loctran.service.entity.post.PostType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsavePostDto {
  private String title;
  private String excerpt;
  private String content;
  private String slug;
  private Boolean isPinned;
  private String thumbnail;
  private PostType type;
  public Post mapToPost(){
    return Post.builder().slug(this.slug).type(this.type).title(this.title).excerpt(this.excerpt).content(this.content).isPinned(this.isPinned).build();
  }


}
