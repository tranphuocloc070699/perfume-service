package com.loctran.service.entity.brand.dto;

import com.loctran.service.entity.media.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBrandDto {
  private String name;
  private String description;
  private String homepageLink;
  private Media thumbnail;
}