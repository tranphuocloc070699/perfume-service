package com.loctran.service.entity.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
