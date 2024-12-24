package com.loctran.service.entity.collection.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsaveCollectionProductDto {
    @NotBlank(message = "Trường này không được để trống")
    private Integer index;

    @NotBlank(message = "Trường này không được để trống")
    private Long productId;
}
