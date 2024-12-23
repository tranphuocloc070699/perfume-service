package com.loctran.service.entity.brand.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {
  private Long id;
  private String name;
  private String description;
  private String homepageLink;
  private String thumbnail;
  private Date createdAt;
  private Date updatedAt;
}
