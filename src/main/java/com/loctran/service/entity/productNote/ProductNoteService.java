package com.loctran.service.entity.productNote;


import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.product.ProductRepository;
import com.loctran.service.entity.productNote.dto.CreateProductNoteDto;
import com.loctran.service.entity.productNote.dto.UpdateProductNoteDto;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductNoteService {
  private final ProductRepository productRepository;
  private final ProductNoteRepository productNoteRepository;
  public List<ProductNote> findAll() {
    return productNoteRepository.findAll();
  }

  public ProductNote findById(Long id) {
    return productNoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductNote","id",id.toString()));
  }

  public ProductNote save(CreateProductNoteDto dto) {
      return productNoteRepository.save(dto.mapToProductNote());
  }



  public ProductNote update(Long id, UpdateProductNoteDto dto) {
    ProductNote productNote = productNoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductNote","id",id.toString()));
    productNote.setName(dto.getName());
    productNote.setSlug(dto.getSlug());
    productNote.setThumbnail(dto.getThumbnail());

    return productNote;
  }



  @Transactional
  public ProductNote addNoteToProduct(Long noteId, Long productId){
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product","id",productId.toString()));
    ProductNote productNote = productNoteRepository.findById(noteId)
        .orElseThrow(() -> new ResourceNotFoundException("ProductNote","id",noteId.toString()));

    if (!productNote.getProducts().contains(product)) {
      productNote.getProducts().add(product);
    }
    return productNoteRepository.save(productNote);
  }

  @Transactional
  public ProductNote removeNoteFromProduct(Long noteId, Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new RuntimeException("Product not found"));
    ProductNote productNote = productNoteRepository.findById(noteId)
        .orElseThrow(() -> new RuntimeException("ProductNote not found"));

    productNote.getProducts().remove(product);

    return productNoteRepository.save(productNote);
  }

  @Transactional
  public ProductNote deleteProductNote(Long noteId) {
    ProductNote productNote = productNoteRepository.findById(noteId)
        .orElseThrow(() -> new RuntimeException("ProductNote not found"));

    for (Product product : productNote.getProducts()) {
      product.getNotes().remove(productNote);
      productRepository.save(product);
    }
    productNoteRepository.delete(productNote);

    return productNote;
  }
}
