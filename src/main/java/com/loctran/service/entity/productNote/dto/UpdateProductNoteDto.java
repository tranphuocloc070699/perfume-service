package com.loctran.service.entity.productNote.dto;

import com.loctran.service.entity.media.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductNoteDto {
  private String name;
  private String slug;
  private Media thumbnail;
}