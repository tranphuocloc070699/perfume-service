package com.loctran.service.entity.collection.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsaveCollectionDto {
    @NotBlank(message = "Trường này không được để trống")
    private String icon;
    @NotBlank(message = "Trường này không được để trống")
    private String title;

@NotNull
    @Size(min = 4, message = "Phải có ít nhất 4 mục trong collectionProducts")
    private Set<UpsaveCollectionProductDto> collectionProducts;
}
