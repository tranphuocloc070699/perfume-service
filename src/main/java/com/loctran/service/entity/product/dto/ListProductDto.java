package com.loctran.service.entity.product.dto;
import com.loctran.service.entity.media.dto.MediaDto;
import java.util.Date;

import com.loctran.service.entity.year.dto.YearDto;
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
  private MediaDto thumbnail;
  private YearDto dateReleased;
  private Date createdAt;
  private Date updatedAt;
}

