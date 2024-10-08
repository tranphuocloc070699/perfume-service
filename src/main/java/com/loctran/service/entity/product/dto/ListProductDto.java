package com.loctran.service.entity.product.dto;

import com.loctran.service.entity.year.dto.YearDto;
import java.util.Date;
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
  private Date createdAt;
  private Date updatedAt;
}

