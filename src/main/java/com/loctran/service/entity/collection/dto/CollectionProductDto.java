package com.loctran.service.entity.collection.dto;
import com.loctran.service.entity.product.dto.ListProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionProductDto {
    private Long id;
    private ListProductDto product;
    private Integer index;
}
