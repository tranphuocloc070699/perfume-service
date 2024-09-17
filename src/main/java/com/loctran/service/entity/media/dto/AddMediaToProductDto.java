package com.loctran.service.entity.media.dto;

import com.loctran.service.entity.media.Media;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddMediaToProductDto {
  Media media;
  String type;
}
