package com.loctran.service.entity.product.dto;

import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.country.Country;
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
  private String thumbnail;
  private List<String> galleries;
  private List<String> outfits;
  private Brand brand;
  private Country country;

  public Product mapToProduct(){
      return Product.builder().name(this.name).slug(this.slug).description(this.description).thumbnail(this.thumbnail).brand(this.brand).country(this.country).build();
  }
}
