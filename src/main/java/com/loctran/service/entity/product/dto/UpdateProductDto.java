package com.loctran.service.entity.product.dto;

import com.loctran.service.entity.media.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDto {
    private String name;
    private String slug;
    private String description;
    private Media thumbnail;
    private Integer dateReleased;

}