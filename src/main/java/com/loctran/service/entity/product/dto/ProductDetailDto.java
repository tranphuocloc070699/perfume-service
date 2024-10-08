package com.loctran.service.entity.product.dto;

import com.loctran.service.entity.brand.dto.BrandDto;
import com.loctran.service.entity.comment.dto.CommentDto;
import com.loctran.service.entity.country.dto.CountryDto;
import com.loctran.service.entity.productCompare.dto.ProductCompareDto;
import com.loctran.service.entity.productNote.dto.ProductNoteDto;
import com.loctran.service.entity.user.dto.UserDto;
import com.loctran.service.entity.year.dto.YearDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ProductDetailDto {
  private Long id;
  private String name;
  private String slug;
  private String description;
  private String thumbnail;
  private Object galleries;
  private List<String> outfits;

  private YearDto dateReleased;
  private List<ProductCompareDto> productCompare;
  private BrandDto brand;
  private CountryDto country;
  private CommentDto comments;
  private ProductNoteDto notes;
  private List<UserDto> votes;
  private Date createdAt;
  private Date updatedAt;


  public ProductDetailDto(Long id, String name, String slug, String description, String thumbnail,
      Object galleries, List<String> outfits, YearDto dateReleased,
      List<ProductCompareDto> productCompare, BrandDto brand, CountryDto country,
      CommentDto comments,
      ProductNoteDto notes, List<UserDto> votes, Date createdAt, Date updatedAt) {
    this.id = id;
    this.name = name;
    this.slug = slug;
    this.description = description;
    this.thumbnail = thumbnail;
    this.galleries = galleries != null ? galleries : new HashSet<>();
    this.outfits = outfits != null ? outfits : new ArrayList<>();
    this.dateReleased = dateReleased;
    this.productCompare = productCompare;
    this.brand = brand;
    this.country = country;
    this.comments = comments;
    this.notes = notes;
    this.votes = votes;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}