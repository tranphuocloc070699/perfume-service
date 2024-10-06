package com.loctran.service.entity.product.dto;

import com.loctran.service.entity.brand.dto.BrandDto;
import com.loctran.service.entity.comment.dto.CommentDto;
import com.loctran.service.entity.country.dto.CountryDto;
import com.loctran.service.entity.media.dto.MediaDto;
import com.loctran.service.entity.productCompare.dto.ProductCompareDto;
import com.loctran.service.entity.productNote.dto.ProductNoteDto;
import com.loctran.service.entity.user.dto.UserDto;
import com.loctran.service.entity.year.dto.YearDto;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDto {
  private Long id;
  private String name;
  private String slug;
  private String description;

  private MediaDto thumbnail;
  private List<MediaDto> galleries;
  private List<MediaDto> outfits;

  private YearDto dateReleased;
  private List<ProductCompareDto> productCompare;
  private BrandDto brand;
  private CountryDto country;
  private CommentDto comments;
  private ProductNoteDto notes;
  private List<UserDto> votes;
  private Date createdAt;
  private Date updatedAt;


  public ProductDetailDto(Long id, String slug, String name, MediaDto thumbnail) {
    this.id = id;
    this.slug = slug;
    this.name = name;
    this.thumbnail = thumbnail;
  }
}