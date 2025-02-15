package com.loctran.service.entity.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
  private Long id;
  private String name;
  private String description;
  private String link;
  private String thumbnail;
}
