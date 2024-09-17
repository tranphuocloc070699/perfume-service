package com.loctran.service.entity.product.dto;

import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.product.Product;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {
  private String name;
  private String slug;
  private String description;
  private Media thumbnail;
  private List<Media> galleries;
  private List<Media> outfits;
  private Brand brand;
  private Country country;
  private Integer dateReleased;

  public Product mapToProduct(){
      return Product.builder().name(this.name).slug(this.slug).description(this.description).thumbnail(this.thumbnail).dateReleased(this.dateReleased).brand(this.brand).country(this.country).build();
  }
}
