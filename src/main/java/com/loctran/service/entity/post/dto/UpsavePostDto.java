package com.loctran.service.entity.post.dto;

import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.post.Post;
import com.loctran.service.entity.post.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsavePostDto {
  private String title;
  private String excerpt;
  private String content;
  private String slug;
  private Boolean isPinned;
  private Long mediaId;
  private PostType type;

}
