package com.loctran.service.entity.productCompare;


import com.loctran.service.exception.custom.ResourceNotFoundException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCompareService {
    private final ProductCompareRepository productCompareRepository;

  public Page<ProductCompare> findByProductOriginalId(Long productOriginalId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return productCompareRepository.findByProductOriginalId(productOriginalId, pageable);
  }

  public ProductCompare findById(Long id) {
    return productCompareRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductCompare","id",id.toString()));
  }

  public ProductCompare createProductCompare(Long id) {
    return productCompareRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductCompare","id",id.toString()));
  }

  public ProductCompare toggleProductCompare(Long productCompareId,Long productId){
      ProductCompare productCompare = findById(productCompareId);
      if(Objects.equals(productCompare.getProductOriginal().getId(), productId)){
        if(productCompare.getOriginalVotes()!=null && productCompare.getOriginalVotes().contains(productId)){

        }
      }else if(Objects.equals(productCompare.getProductCompare().getId(), productId)){

      }


  }
}
