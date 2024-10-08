package com.loctran.service.entity.productCompare.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCompareDto {
  private Long productOriginalId;
  private Long productCompareId;
}
