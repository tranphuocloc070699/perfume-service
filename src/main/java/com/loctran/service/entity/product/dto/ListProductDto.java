package com.loctran.service.entity.product.dto;

import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.productPrice.ProductPrice;
import com.loctran.service.entity.productPrice.dto.ProductPriceDto;
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
public class ListProductDto {
  private Long id;
  private String name;
  private String slug;
  private String description;
  private String thumbnail;
  private YearDto dateReleased;
  private List<ProductPrice> prices;
  private Date createdAt;
  private Date updatedAt;


  public void mapFromProduct(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.slug = product.getSlug();
    this.description = product.getDescription();
    this.thumbnail = product.getThumbnail();
    this.dateReleased = new YearDto(product.getDateReleased().getId(), product.getDateReleased().getValue());
    this.prices = product.getPrices();
    this.createdAt = product.getCreatedAt();
    this.updatedAt = product.getUpdatedAt();
  }


}

