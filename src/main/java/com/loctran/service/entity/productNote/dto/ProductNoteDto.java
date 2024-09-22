package com.loctran.service.entity.productNote.dto;

import com.loctran.service.entity.media.dto.MediaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductNoteDto {
  private Long id;
  private String name;
  private String slug;
  private MediaDto thumbnail;
}
