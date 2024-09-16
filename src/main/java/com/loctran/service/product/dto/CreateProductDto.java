package com.loctran.service.product.dto;

import com.loctran.service.media.Media;
import com.loctran.service.product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
  private Integer dateReleased;

  public Product mapToProduct(){
      return Product.builder().name(this.name).slug(this.slug).description(this.description).thumbnail(this.thumbnail).dateReleased(this.dateReleased).build();
  }
}
