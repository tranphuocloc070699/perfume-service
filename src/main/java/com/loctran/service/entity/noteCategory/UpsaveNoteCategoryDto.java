package com.loctran.service.entity.noteCategory;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsaveNoteCategoryDto {

  private String title;
  private String description;
  private String thumbnail;

}