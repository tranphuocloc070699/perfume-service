package com.loctran.service.entity.productPrice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.productPrice.LabelType;
import com.loctran.service.entity.productPrice.PriceType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceDto {
  private Long id;
  private LabelType labelType;
  private PriceType priceType;
  private Long value;
  private Boolean isSearch;
  private String link;
  private Date createdAt;
  private Date updatedAt;
}
