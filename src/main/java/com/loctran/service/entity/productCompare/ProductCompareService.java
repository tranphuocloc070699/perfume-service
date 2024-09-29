package com.loctran.service.entity.productCompare;


import com.loctran.service.exception.custom.ResourceNotFoundException;
import java.util.ArrayList;
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

  public ProductCompare deleteProductCompare(Long id) {
    ProductCompare productCompare = findById(id);
    productCompareRepository.delete(productCompare);
    return productCompare;
  }

  public ProductCompare toggleProductCompareVote(Long productCompareId,Long productId,Long userId){
      ProductCompare productCompare = findById(productCompareId);
      if(Objects.equals(productCompare.getProductOriginal().getId(), productId)){
        if(productCompare.getOriginalVotes()==null){
          productCompare.setOriginalVotes(new ArrayList<>());
          productCompare.getOriginalVotes().add(userId);
        }else{
          productCompare.getOriginalVotes().add(userId);
        }

        if(productCompare.getCompareVotes()!=null && productCompare.getCompareVotes().contains(userId)){
          productCompare.getCompareVotes().remove(userId);
        }
      }else if(Objects.equals(productCompare.getProductCompare().getId(), productId)){
        if(productCompare.getCompareVotes()==null){
          productCompare.setCompareVotes(new ArrayList<>());
          productCompare.getCompareVotes().add(userId);
        }else{
          productCompare.getCompareVotes().add(userId);
        }

        if(productCompare.getOriginalVotes()!=null && productCompare.getOriginalVotes().contains(userId)){
          productCompare.getOriginalVotes().remove(userId);
        }
      }

    return productCompareRepository.save(productCompare);
  }
}
