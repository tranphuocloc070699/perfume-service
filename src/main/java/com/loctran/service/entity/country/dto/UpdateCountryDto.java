package com.loctran.service.entity.country.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCountryDto {
  private String name;
  private String code;
  private String thumbnail;
}