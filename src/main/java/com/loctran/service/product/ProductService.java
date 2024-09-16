package com.loctran.service.product;

import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.media.Media;
import com.loctran.service.media.MediaRepository;
import com.loctran.service.product.dto.CreateProductDto;
import com.loctran.service.product.dto.UpdateProductDto;
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

    mediaRepository.save(dto.getThumbnail());
    dto.getGalleries().forEach(gallery -> {
      gallery.setProduct(productSaved);
      gallerySavedList.add(mediaRepository.save(gallery));
    });
    dto.getOutfits().forEach(outfit -> {
      outfit.setProduct(productSaved);
      outfitSavedList.add(mediaRepository.save(outfit));
    });
    product.setGalleries(gallerySavedList);
    product.setOutfits(outfitSavedList);
    return product;
  }

  public Product findProductBySlug(String slug){
    Product product = productRepository.findBySlug(slug).orElseThrow(() -> new ResourceNotFoundException("Product","slug",slug));

    return product;
  }

  public Product updateProduct(Long id, UpdateProductDto dto){
    Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","id",id.toString()));

  }

  public Media addProductGallery(){
    return null;
  }

}
