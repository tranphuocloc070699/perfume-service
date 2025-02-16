package com.loctran.service.entity.country;

import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.brand.BrandService;
import com.loctran.service.entity.country.dto.CreateCountryDto;
import com.loctran.service.entity.country.dto.UpsaveCountryDto;

import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

  private final CountryRepository countryRepository;

  private final BrandService brandService;
  private final ModelMapper modelMapper;

  public List<Country> findAll() {
    return countryRepository.findAll();
  }

  public Country findById(Long id) {
    return countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
        ResponseMessage.DATA_NOT_FOUND));
  }

  public Country create(UpsaveCountryDto dto) {
    Country country = modelMapper.map(dto,Country.class);


    return countryRepository.save(country);
  }



  public Country updateById(Long id, UpsaveCountryDto dto) {
    Country country = countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    country.setName(dto.getName());
    country.setCode(dto.getCode());
    country.setThumbnail(dto.getThumbnail());

    modelMapper.map(dto,country);
    return countryRepository.save(country);
  }



  @Transactional
  public Country addBrandToCountry(Long countryId, Long brandId) {
    Brand brand = brandService.findById(brandId);
    Country country = findById(countryId);
    if (!country.getBrands().contains(brand)) {
      country.getBrands().add(brand);

    }
    return countryRepository.save(country);
  }

  @Transactional
  public Country removeBrandFromCountry( Long countryId,Long brandId) {
    Brand brand = brandService.findById(brandId);
    Country country = findById(countryId);
    country.getBrands().remove(brand);
    return countryRepository.save(country);
  }


  @Transactional
  public Country deleteById(Long id) {
    Country country = countryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Country not found"));
    countryRepository.delete(country);
    return country;
  }
}
