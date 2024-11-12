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
public class ProductNoteDto {
  private Long id;
  private String name;
  private String slug;
  private String enName;
  private String thumbnail;

  public ProductNote mapToEntity(){
    return ProductNote.builder().id(this.id).name(this.name).enName(this.enName).slug(this.slug).thumbnail(this.thumbnail).build();
  }
}
