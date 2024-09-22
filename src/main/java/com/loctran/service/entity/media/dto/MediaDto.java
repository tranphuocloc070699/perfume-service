package com.loctran.service.entity.media.dto;

import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.media.MediaType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaDto {
  private Long id;
  private String path;
  private MediaType type;
}

