package com.loctran.service.product;

import com.loctran.service.media.Media;
import com.loctran.service.media.MediaRepository;
import com.loctran.service.product.dto.CreateProductDto;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

  private final MediaRepository mediaRepository;
  private final ProductRepository productRepository;

  public Product createProduct(CreateProductDto dto) {
    Product product = dto.mapToProduct();
    Product productSaved = productRepository.save(product);
    List<Media> gallerySavedList = new ArrayList<>();
    List<Media> outfitSavedList = new ArrayList<>();
    dto.getGallery().forEach(gallery -> {
      gallery.setProductGallery(productSaved);
      gallerySavedList.add(mediaRepository.save(gallery));
    });
    dto.getOutfit().forEach(outfit -> {
      outfit.setProductOutfit(productSaved);
      outfitSavedList.add(mediaRepository.save(outfit));
    });
    product.setGallery(gallerySavedList);
    product.setOutfit(outfitSavedList);
    return product;
  }

  public Media addProductGallery(){

  }

}
