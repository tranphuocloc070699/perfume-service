package com.loctran.service.entity.product;

import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.brand.BrandRepository;
import com.loctran.service.entity.product.dto.CreateProductDto;
import com.loctran.service.entity.product.dto.ListProductDto;
import com.loctran.service.entity.product.dto.ProductDetailDto;
import com.loctran.service.entity.product.dto.UpdateProductDto;
import com.loctran.service.entity.productCompare.ProductCompare;
import com.loctran.service.entity.productCompare.ProductCompareRepository;
import com.loctran.service.entity.productCompare.dto.ListProductCompareDto;
import com.loctran.service.entity.productCompare.dto.ProductCompareDto;
import com.loctran.service.entity.productPrice.ProductPrice;
import com.loctran.service.entity.productPrice.ProductPriceRepository;
import com.loctran.service.entity.year.Year;
import com.loctran.service.entity.year.YearService;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import jakarta.transaction.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

  private final ProductRepository productRepository;
  private final YearService yearService;
  private final ProductCompareRepository productCompareRepository;
  private final ProductPriceRepository productPriceRepository;
  private final BrandRepository brandRepository;


  public Page<ListProductDto> getAllProduct(int page, int size,String sortBy,String sortDir,Long brandId, Long countryId, List<Long> notesIds,String productName) {
    Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
    Pageable pageable = PageRequest.of(page-1, size,sort);



    return productRepository.findAllProducts(pageable,brandId,countryId,notesIds,productName);
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
    product.setThumbnail(dto.getThumbnail());
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


//  public Media addProductGallery(Long productId, MultipartFile file){
//    Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product","id",productId.toString()));
//
//    String fileDir = "product-" + productId;
//    try {
//     String path = FileUploadUtil.saveFile(fileDir,file.getOriginalFilename(),file);
//      return mediaRepository.save(Media.builder().path(path).type(MediaType.PRODUCT_GALLERY).product(product).build());
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//  }

//  public Media addProductOutfit(Long productId, MultipartFile file){
//    Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product","id",productId.toString()));
//    String fileDir = "product-" + productId;
//    try {
//      String path = FileUploadUtil.saveFile(fileDir,file.getOriginalFilename(),file);
//      return mediaRepository.save(Media.builder().path(path).type(MediaType.PRODUCT_OUTFIT).product(product).build());
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//  }



  public Product deleteProduct(Long id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","id",id.toString()));

    productRepository.delete(product);
    return product;
  }

  public Product findProductById(Long id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","id",id.toString()));
    List<Object[]> objectResponse = productCompareRepository.findProductCompare(id);
    List<ListProductCompareDto> listProductCompareDtos = new ArrayList<>();
    objectResponse.forEach((object) -> {
      ListProductCompareDto dto = new ListProductCompareDto();
      dto.convertObjectToDto(object);
      listProductCompareDtos.add(dto);
    });
//
//    if (product.getBrand().getId()!=null) {
//      List<Object[]> brandRawData = brandRepository.findBrandById((product.getBrand().getId()));
//      System.out.println("size:" + brandRawData.size());
//      Object[] firstBrandRawData = brandRawData.getFirst();
//      //b.name,b.description,b.homepageLink,b.thumbnail
//      String brandName = firstBrandRawData[1]!=null ? firstBrandRawData[1].toString() : "";
//      String brandDesc = firstBrandRawData[2]!=null ? firstBrandRawData[2].toString() : "";
//      String brandHomepageLink = firstBrandRawData[3]!=null ? firstBrandRawData[3].toString() : "";
//      String brandThumbnail = firstBrandRawData[4]!=null ? firstBrandRawData[4].toString() : "";
//
//      Brand brand = Brand.builder().id(product.getBrand().getId()).name(brandName).description(brandDesc).homepageLink(brandHomepageLink).thumbnail(brandThumbnail).build();
//      product.setBrand(brand);
//     }

    product.setProductCompares(listProductCompareDtos);

    return product;
  }

  public ProductCompareDto findProductCompare(Long id) {
  ProductCompare productCompareExisting = productCompareRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductCompare","id",id.toString()));

  List<Object[]> rawDataResponse = productCompareRepository.findByProductCompareId(id);
    Object[] rawData = rawDataResponse.get(0);

    Long productOriginalId = rawData[0]!=null ? (Long) rawData[0] : 0L;
    String productOriginalName = rawData[1]!=null ?  rawData[1].toString() : "";
    String productOriginalThumbnail = rawData[2]!=null ?  rawData[2].toString() : "";

    Long productCompareId = rawData[3]!=null ? (Long) rawData[3] : 0;
    String productCompareName = rawData[4]!=null ?  rawData[4].toString() : "";
    String productCompareThumbnail = rawData[5]!=null ?  rawData[5].toString() : "";



  Product productOriginal = Product.builder().id(productOriginalId).name(productOriginalName).thumbnail(productOriginalThumbnail).build();
  Product productCompare = Product.builder().id(productCompareId).name(productCompareName).thumbnail(productCompareThumbnail).build();
  List<ProductPrice> productOriginalPrices = productPriceRepository.findByProductId(productOriginalId);
  List<ProductPrice> productComparePrices = productPriceRepository.findByProductId(productCompareId);
  productOriginal.setPrices(productOriginalPrices);
    productCompare.setPrices(productComparePrices);

    productCompareExisting.setProductOriginal(productOriginal);
    productCompareExisting.setProductCompare(productCompare);

    ProductCompareDto dto = new ProductCompareDto();
    dto.fillData(productCompareExisting);

  return dto;
  }
}
