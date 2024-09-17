package com.loctran.service.entity.country;

import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.brand.BrandService;
import com.loctran.service.entity.country.dto.CreateCountryDto;
import com.loctran.service.entity.country.dto.UpdateCountryDto;
import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.media.MediaRepository;
import com.loctran.service.entity.media.MediaType;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.product.ProductRepository;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService {

  private final CountryRepository countryRepository;
  private final MediaRepository mediaRepository;
  private final BrandService brandService;

  public List<Country> findAll() {
    return countryRepository.findAll();
  }

  public Country findById(Long id) {
    return countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country","id",id.toString()));
  }

  public Country save(CreateCountryDto dto) {

    mediaRepository.save(dto.getThumbnail());
    return countryRepository.save(dto.maptoCountry());
  }



  public Country update(Long id, UpdateCountryDto dto) {
    Country country = countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country","id",id.toString()));
    country.setName(dto.getName());
    country.setCode(dto.getCode());
    if(dto.getThumbnail().getId()==null && !dto.getThumbnail().getPath().equals(country.getThumbnail().getPath())){
      Media thumbnail = dto.getThumbnail();
      thumbnail.setType(MediaType.COUNTRY_THUMBNAIL);
      thumbnail.setCountry(country);
      country.setThumbnail(mediaRepository.save(thumbnail));
    }else{
      country.setThumbnail(dto.getThumbnail());
    }
    return country;
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



//  @Transactional
//  public ProductNote removeNoteFromProduct(Long noteId, Long productId) {
//    Product product = productRepository.findById(productId)
//        .orElseThrow(() -> new RuntimeException("Product not found"));
//    ProductNote productNote = productNoteRepository.findById(noteId)
//        .orElseThrow(() -> new RuntimeException("ProductNote not found"));
//
//    if (productNote.getProducts().contains(product)) {
//      productNote.getProducts().remove(product);
//    }
//
//    return productNoteRepository.save(productNote);
//  }

  @Transactional
  public Country deleteCountry(Long id) {
    Country country = countryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Country not found"));

//    for (Product product : productNote.getProducts()) {
//      product.getNotes().remove(productNote);
//      productRepository.save(product);
//    }
    countryRepository.delete(country);
    return country;
  }
}
