package com.loctran.service.entity.country.dto;

import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.media.dto.MediaDto;
import com.loctran.service.entity.product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.Date;
import java.util.List;
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
public class CountryDto {
  private Long id;
  private String name;
  private String code;
  private MediaDto thumbnail;
  private Date createdAt;
  private Date updatedAt;
}
