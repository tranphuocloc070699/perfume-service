package com.loctran.service.entity.year.dto;

import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.year.Year;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class YearDto {
    private Long id;
    private Integer value;

    public Year mapToYear(){
        return Year.builder().id(this.id).value(this.value).build();
    }
}