package com.loctran.service.product.dto;

import com.loctran.service.media.Media;
import com.loctran.service.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    public Product mapToProduct(){
        return Product.builder().name(this.name).slug(this.slug).description(this.description).thumbnail(this.thumbnail).dateReleased(this.dateReleased).build();
    }
}