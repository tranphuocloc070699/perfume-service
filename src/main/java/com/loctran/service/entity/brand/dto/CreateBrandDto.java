package com.loctran.service.entity.brand.dto;

import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.media.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBrandDto {
  private String name;
  private String description;
  private String homepageLink;
  private Media thumbnail;
  public Brand mapToBrand(){
    return Brand.builder().name(this.name).description(this.description).homepageLink(this.homepageLink).thumbnail(this.thumbnail).build();
  }
}
