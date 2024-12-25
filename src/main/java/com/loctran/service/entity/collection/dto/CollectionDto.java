package com.loctran.service.entity.collection.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDto {
  private Long id;
  private String icon;
  private String title;
  private Integer index;
  private Set<CollectionProductDto> collectionProducts;
}
