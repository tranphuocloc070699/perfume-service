package com.loctran.service.entity.post.dto;

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
  private String thumbnail;
}
