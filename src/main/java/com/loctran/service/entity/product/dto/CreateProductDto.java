package com.loctran.service.entity.product.dto;

import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.productNote.ProductNote;
import com.loctran.service.entity.productPrice.ProductPrice;
import com.loctran.service.entity.year.Year;
import java.util.List;
import java.util.Set;
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
  private Set<String> galleries;
  private Set<String> outfits;
  private Brand brand;
  private Country country;
  private Year dateReleased;
  private Set<ProductNote> topNotes;
  private Set<ProductNote> middleNotes;
  private Set<ProductNote> baseNotes;
  private List<ProductPrice> prices;

  public Product mapToProduct(){
    return Product.builder()
        .name(this.name)
        .slug(this.slug)
        .description(this.description)
        .thumbnail(this.thumbnail)
        .galleries(this.galleries)
        .outfits(this.outfits)
        .brand(this.brand)
        .country(this.country)
        .dateReleased(this.dateReleased)
        .topNotes(this.topNotes)
        .middleNotes(this.middleNotes)
        .baseNotes(this.baseNotes)
        .prices(this.prices)
        .build();
  }
}
