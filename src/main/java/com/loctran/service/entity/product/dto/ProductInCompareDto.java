package com.loctran.service.entity.product.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInCompareDto {
    private Long id;
    private String name;
    private String thumbnail;
}
