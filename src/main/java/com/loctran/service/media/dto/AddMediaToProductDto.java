package com.loctran.service.media.dto;

import com.loctran.service.media.Media;
import java.util.List;
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
