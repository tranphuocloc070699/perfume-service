package com.loctran.service.entity.brand;

import com.loctran.service.entity.brand.dto.CreateBrandDto;
import com.loctran.service.entity.brand.dto.UpdateBrandDto;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.country.CountryRepository;
import com.loctran.service.entity.country.CountryService;
import com.loctran.service.entity.country.dto.CreateCountryDto;
import com.loctran.service.entity.country.dto.UpdateCountryDto;

import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

  private final BrandRepository brandRepository;



  public List<Brand> findAll() {
    return brandRepository.findAll();
  }

  public Brand findById(Long id) {
    return brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
        ResponseMessage.DATA_NOT_FOUND));
  }

  public Brand save(CreateBrandDto dto) {
    return brandRepository.save(dto.mapToBrand());
  }


  public Brand update(Long id, UpdateBrandDto dto) {
    Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    brand.setName(dto.getName());
    brand.setDescription(dto.getDescription());
    brand.setHomepageLink(dto.getHomepageLink());
    brand.setThumbnail(dto.getThumbnail());

    return brand;
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
  public Brand deleteBrand(Long id) {
    Brand country = brandRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Brand not found"));
    brandRepository.delete(country);
    return country;
  }
}
