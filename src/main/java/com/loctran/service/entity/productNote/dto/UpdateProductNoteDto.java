package com.loctran.service.entity.productNote.dto;

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
  private String thumbnail;
}