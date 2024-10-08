package com.loctran.service.entity.country.dto;

import com.loctran.service.entity.country.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCountryDto {
  private String name;
  private String code;
  private String thumbnail;
  public Country maptoCountry(){
    return Country.builder().name(this.name).code(this.code).thumbnail(this.thumbnail).build();
  }
}
