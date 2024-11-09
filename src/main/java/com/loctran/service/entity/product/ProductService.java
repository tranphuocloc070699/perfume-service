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
import com.loctran.service.entity.productNote.ProductNote;
import com.loctran.service.entity.productPrice.LabelType;
import com.loctran.service.entity.productPrice.ProductPrice;
import com.loctran.service.entity.productPrice.ProductPriceRepository;
import com.loctran.service.entity.year.Year;
import com.loctran.service.entity.year.YearService;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import jakarta.transaction.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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


  public Page<ListProductDto> getAllProduct(int page, int size, String sortBy, String sortDir,
      Long brandId, Long countryId, List<Long> notesIds, String productName) {
    Sort sort = Sort.unsorted();  // Default to unsorted
    List<String> sortByPrice = Arrays.asList("price_LISTED", "price_VIETNAM_MARKET");

    // Check if sorting by 'dateReleased'
    if ("dateReleased".equals(sortBy)) {
      sort = sortDir.equalsIgnoreCase("desc")
          ? Sort.by("dateReleased.value").descending()
          : Sort.by("dateReleased.value").ascending();
    }
    // Check if sorting by price (optional)
    else if (sortByPrice.contains(sortBy)) {
      Pageable pageable = PageRequest.of(page - 1, size, sort);
      Page<ListProductDto> response = productRepository.findAllProducts(pageable, brandId,
          countryId, notesIds, productName);
      ;
      response.get().forEach(listProductDto -> {
        List<ProductPrice> prices = productPriceRepository.findByProductId(listProductDto.getId());
        listProductDto.setPrices(prices);
      });
      Comparator<ListProductDto> priceComparator = Comparator.comparing(listProductDto -> {
        LabelType labelType = "price_LISTED".equals(sortBy) ? LabelType.LISTED : LabelType.VIETNAM_MARKET;
        ProductPrice productPrice = listProductDto.getPrices().stream()
            .filter(price -> price.getLabelType().equals(labelType))
            .findFirst()
            .orElse(null);
        return productPrice != null ? productPrice.getValue() : 0L;
      });

      if ("desc".equalsIgnoreCase(sortDir)) {
        priceComparator = priceComparator.reversed();
      }

      List<ListProductDto> sortedProducts = response.getContent().stream()
          .sorted(priceComparator)
          .collect(Collectors.toList());

      return new PageImpl<>(sortedProducts, pageable, response.getTotalElements());
    }
    else if (sortBy != null && !sortBy.isEmpty()) {
      sort = sortDir.equalsIgnoreCase("desc")
          ? Sort.by(sortBy).descending()
          : Sort.by(sortBy).ascending();
    }

    Pageable pageable = PageRequest.of(page - 1, size, sort);
    return productRepository.findAllProducts(pageable, brandId, countryId, notesIds, productName);
  }

  public List<Long> getAllProductId() {
    return productRepository.findAllIds();
  }

  public Product createProduct(CreateProductDto dto) {
    Product product = dto.mapToProduct();

    // Set the product reference in each ProductPrice
    product.getPrices().forEach(price -> price.setProduct(product));

    // Save the product, which will also save prices due to cascade setting
    return productRepository.save(product);
  }

  public Product findProductBySlug(String slug) {
    Product product = productRepository.findById(1L)
        .orElseThrow(() -> new ResourceNotFoundException("Product", "slug", slug));

    return product;
  }


  public Product updateProduct(Long id, CreateProductDto dto) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id.toString()));

    List<ProductPrice> productPrices = dto.getPrices().stream().map(productPrice -> {
      productPrice.setProduct(product);
      return productPrice;
    }).collect(Collectors.toList());

    product.setName(dto.getName());
    product.setSlug(dto.getSlug());
    product.setThumbnail(dto.getThumbnail());
    product.setDescription(dto.getDescription());
    product.setGalleries(dto.getGalleries());
    product.setOutfits(dto.getOutfits());
    product.setPrices(productPrices);
    product.setDateReleased(dto.getDateReleased());
    product.setBrand(dto.getBrand());
    product.setCountry(dto.getCountry());
    product.setTopNotes(dto.getTopNotes());
    product.setMiddleNotes(dto.getMiddleNotes());
    product.setBaseNotes(dto.getBaseNotes());


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
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id.toString()));

    productRepository.delete(product);
    return product;
  }

  public Product findProductById(Long id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id.toString()));
    List<Object[]> objectResponse = productCompareRepository.findProductCompare(id);
    List<ListProductCompareDto> listProductCompareDtos = new ArrayList<>();
    objectResponse.forEach((object) -> {
      ListProductCompareDto dto = new ListProductCompareDto();
      dto.convertObjectToDto(object);
      listProductCompareDtos.add(dto);
    });
    product.setProductCompares(listProductCompareDtos);

    return product;
  }

  public ProductCompareDto findProductCompare(Long id) {
    ProductCompare productCompareExisting = productCompareRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("ProductCompare", "id", id.toString()));

    List<Object[]> rawDataResponse = productCompareRepository.findByProductCompareId(id);
    Object[] rawData = rawDataResponse.get(0);

    Long productOriginalId = rawData[0] != null ? (Long) rawData[0] : 0L;
    String productOriginalName = rawData[1] != null ? rawData[1].toString() : "";
    String productOriginalThumbnail = rawData[2] != null ? rawData[2].toString() : "";

    Long productCompareId = rawData[3] != null ? (Long) rawData[3] : 0;
    String productCompareName = rawData[4] != null ? rawData[4].toString() : "";
    String productCompareThumbnail = rawData[5] != null ? rawData[5].toString() : "";

    Product productOriginal = Product.builder().id(productOriginalId).name(productOriginalName)
        .thumbnail(productOriginalThumbnail).build();
    Product productCompare = Product.builder().id(productCompareId).name(productCompareName)
        .thumbnail(productCompareThumbnail).build();
    List<ProductPrice> productOriginalPrices = productPriceRepository.findByProductId(
        productOriginalId);
    List<ProductPrice> productComparePrices = productPriceRepository.findByProductId(
        productCompareId);
    productOriginal.setPrices(productOriginalPrices);
    productCompare.setPrices(productComparePrices);

    productCompareExisting.setProductOriginal(productOriginal);
    productCompareExisting.setProductCompare(productCompare);

    ProductCompareDto dto = new ProductCompareDto();
    dto.fillData(productCompareExisting);

    return dto;
  }
}
