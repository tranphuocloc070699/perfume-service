package com.loctran.service.entity.brand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsaveBrandDto {
  private String name;
  private String description;
  private String homepageLink;
  private String thumbnail;
  private Long countryId;

}
