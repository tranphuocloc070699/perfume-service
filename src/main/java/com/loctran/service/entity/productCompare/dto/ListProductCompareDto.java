package com.loctran.service.entity.productCompare.dto;


import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.product.dto.ProductInCompareDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListProductCompareDto {
    private Long id;
    private String createdAt;
    private String updatedAt;
    private Product product;


    public void convertObjectToDto(Object[] objects){
        long id = objects[0]!=null  ? (Long) objects[0] : 0L;
        String createdAt =  objects[1].toString();
        String updatedAt =  objects[2].toString();

        Long productCompareId =  objects[3]!=null ? (Long) objects[3] : 0L;
        String productCompareName =  objects[4]!=null ? objects[4].toString() :  "" ;
        String productCompareThumbnail = objects[5]!=null ?  objects[5].toString() : "";


        Product dto = new Product();
        dto.setId(productCompareId);
        dto.setName(productCompareName);
        dto.setThumbnail(productCompareThumbnail);

        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.product = dto;
    }
}
