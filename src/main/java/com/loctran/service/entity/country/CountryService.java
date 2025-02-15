package com.loctran.service.entity.country;

import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.brand.BrandService;
import com.loctran.service.entity.country.dto.CreateCountryDto;
import com.loctran.service.entity.country.dto.UpdateCountryDto;

import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

  private final CountryRepository countryRepository;

  private final BrandService brandService;

  public List<Country> findAll() {
    return countryRepository.findAll();
  }

  public Country findById(Long id) {
    return countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
        ResponseMessage.DATA_NOT_FOUND));
  }

  public Country save(CreateCountryDto dto) {
    return countryRepository.save(dto.maptoCountry());
  }



  public Country update(Long id, UpdateCountryDto dto) {
    Country country = countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    country.setName(dto.getName());
    country.setCode(dto.getCode());
    country.setThumbnail(dto.getThumbnail());
    Country countrySaved = countryRepository.save(country);
    return countrySaved;
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
  public Country deleteCountry(Long id) {
    Country country = countryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Country not found"));
    countryRepository.delete(country);
    return country;
  }
}
