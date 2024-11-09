package com.loctran.service.entity.productNote.dto;

import com.loctran.service.entity.productNote.ProductNote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductNoteDto {
  private String name;
  private String slug;
  private String enName;
  private String thumbnail;
  public ProductNote mapToProductNote(){
    return ProductNote.builder().name(this.name).enName(this.enName).slug(this.slug).thumbnail(this.thumbnail).build();
  }
}
