package com.loctran.service.entity.product;

import com.loctran.service.entity.media.MediaType;
import com.loctran.service.entity.product.dto.CreateProductDto;
import com.loctran.service.entity.product.dto.ListProductDto;
import com.loctran.service.entity.product.dto.UpdateProductDto;
import com.loctran.service.entity.year.Year;
import com.loctran.service.entity.year.YearService;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.media.MediaRepository;
import com.loctran.service.utils.FileUploadUtil;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

  private final ProductRepository productRepository;
  private final YearService yearService;


  public Page<ListProductDto> getAllProduct(int page, int size,String sortBy,String sortDir,Long brandId, Long countryId, List<Long> notesIds) {
    Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
    Pageable pageable = PageRequest.of(page, size,sort);
    return productRepository.findAllProducts(pageable,brandId,countryId,notesIds);
  }

  public List<Long> getAllProductId(){
    return productRepository.findAllIds();
  }

  public Product createProduct(CreateProductDto dto) {
    Product product = dto.mapToProduct();

    Product productSaved = productRepository.save(product);
//    List<Media> gallerySavedList = new ArrayList<>();
//    List<Media> outfitSavedList = new ArrayList<>();
//
//    mediaRepository.save(dto.getThumbnail());
//    dto.getGalleries().forEach(gallery -> {
//      gallery.setProduct(productSaved);
//      gallerySavedList.add(mediaRepository.save(gallery));
//    });
//    dto.getOutfits().forEach(outfit -> {
//      outfit.setProduct(productSaved);
//      outfitSavedList.add(mediaRepository.save(outfit));
//    });
//    product.setGalleries(gallerySavedList);
//    product.setOutfits(outfitSavedList);
    return product;
  }

  public Product findProductBySlug(String slug){
    Product product = productRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Product","slug",slug));

    return product;
  }

  public Product updateProduct(Long id, UpdateProductDto dto){
    Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","id",id.toString()));
    product.setName(dto.getName());
    product.setDescription(dto.getDescription());
    if(dto.getThumbnail().getId()==null && !dto.getThumbnail().getPath().equals(product.getThumbnail().getPath())){
     Media thumbnail = dto.getThumbnail();
     thumbnail.setProduct(product);
     product.setThumbnail(mediaRepository.save(thumbnail));
    }else{
      product.setThumbnail(dto.getThumbnail());
    }
    product.setSlug(dto.getSlug());
    if(!Objects.equals(dto.getDateReleased(), product.getDateReleased().getValue())){
      Year year = yearService.findByValue(dto.getDateReleased());
      product.setDateReleased(year);
    }
//    product.setDateReleased(dto.getDateReleased());
    return productRepository.save(product);
  }

  public Product toggleVote(Long userId, Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId.toString()));
    if (product.getVotes() == null) {
      product.setVotes(new ArrayList<>());
    }

//    if (product.getVotes().contains(userId)) {
//      product.getVotes().remove(userId);
//    } else {
//      product.getVotes().add(userId);
//    }
    return productRepository.save(product);
  }


  public Media addProductGallery(Long productId, MultipartFile file){
    Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product","id",productId.toString()));

    String fileDir = "product-" + productId;
    try {
     String path = FileUploadUtil.saveFile(fileDir,file.getOriginalFilename(),file);
      return mediaRepository.save(Media.builder().path(path).type(MediaType.PRODUCT_GALLERY).product(product).build());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Media addProductOutfit(Long productId, MultipartFile file){
    Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product","id",productId.toString()));
    String fileDir = "product-" + productId;
    try {
      String path = FileUploadUtil.saveFile(fileDir,file.getOriginalFilename(),file);
      return mediaRepository.save(Media.builder().path(path).type(MediaType.PRODUCT_OUTFIT).product(product).build());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }



  public Product deleteProduct(Long id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","id",id.toString()));

    productRepository.delete(product);
    return product;
  }

  public Product findProductById(Long id) {
    System.out.println("findProductById");
    Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","id",id.toString()));

    return product;
  }
}
