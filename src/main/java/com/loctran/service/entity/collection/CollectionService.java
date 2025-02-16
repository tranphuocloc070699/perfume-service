package com.loctran.service.entity.collection;

import com.loctran.service.entity.collection.dto.CollectionDto;
import com.loctran.service.entity.collection.dto.CollectionProductDto;
import com.loctran.service.entity.collection.dto.UpsaveCollectionDto;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.product.ProductRepository;
import com.loctran.service.entity.product.dto.ListProductDto;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import java.util.ArrayList;
import java.util.Comparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.LinkedHashSet;
@Service
@RequiredArgsConstructor
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final ProductRepository productRepository;
    private final CollectionProductRepository collectionProductRepository;


  public List<CollectionDto> getAll() {
    List<Collection> collections = collectionRepository.findAllByOrderByIndexAsc();
    List<CollectionDto> collectionDtos = new ArrayList<>();

    for (Collection collection : collections) {
      List<CollectionProduct> collectionProducts = collectionProductRepository.findByCollectionId(collection.getId());
      Set<CollectionProductDto> collectionProductDtos = collectionProducts.stream()
          .map(collectionProduct -> {
            Product product = productRepository.findById(collectionProduct.getProduct().getId()).get();
            CollectionProductDto collectionProductDto = new CollectionProductDto();
            collectionProductDto.setId(product.getId());
            ListProductDto listProductDto = new ListProductDto();
            listProductDto.mapFromProduct(product);
            collectionProductDto.setProduct(listProductDto);
            collectionProductDto.setIndex(collectionProduct.getIndex());
            return collectionProductDto;
          })
          .sorted(Comparator.comparingInt(CollectionProductDto::getIndex))
          .collect(Collectors.toCollection(LinkedHashSet::new));

      collectionDtos.add(new CollectionDto(collection.getId(), collection.getIcon(), collection.getTitle(),
          collection.getIndex(), collectionProductDtos));
    }
    return collectionDtos;
  }



    public Collection create(UpsaveCollectionDto upsaveCollectionDto) {
        long totalCollection =  collectionRepository.count();


        Collection collection = new Collection();
        collection.setIcon(upsaveCollectionDto.getIcon());
        collection.setTitle(upsaveCollectionDto.getTitle());
        collection.setIndex(Integer.parseInt(String.valueOf(totalCollection)));

        Collection collectionSaved = collectionRepository.save(collection);
        Set<CollectionProduct> collectionProducts = upsaveCollectionDto.getCollectionProducts().stream()
                .map(productDto -> {
                    CollectionProduct collectionProduct = new CollectionProduct();
                    collectionProduct.setIndex(productDto.getIndex());
                    collectionProduct.setProduct(productRepository.findById(productDto.getProductId()).orElseThrow(() ->
                            new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND)));
                    collectionProduct.setCollection(collectionSaved);
                    return  collectionProductRepository.save(collectionProduct);
                }).collect(Collectors.toSet());
        collectionSaved.setCollectionProducts(collectionProducts);
 return collectionSaved;
    }


    public Optional<Collection> updateById(Long id, UpsaveCollectionDto upsaveCollectionDto) {
        Optional<Collection> optionalCollection = collectionRepository.findById(id);
        if (optionalCollection.isPresent()) {
            Collection collection = optionalCollection.get();
            collection.setIcon(upsaveCollectionDto.getIcon());
            collection.setTitle(upsaveCollectionDto.getTitle());
            Collection collectionSaved = collectionRepository.save(collection);
            collectionProductRepository.deleteByCollectionId(collectionSaved.getId());
            Set<CollectionProduct> updatedCollectionProducts = upsaveCollectionDto.getCollectionProducts().stream()
                    .map(productDto -> {

                        CollectionProduct collectionProduct = new CollectionProduct();
                        collectionProduct.setIndex(productDto.getIndex());
                        collectionProduct.setProduct(productRepository.findById(productDto.getProductId()).orElseThrow(() ->
                                new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND)));
                        collectionProduct.setCollection(collectionSaved);
                        return  collectionProductRepository.save(collectionProduct);
                    }).collect(Collectors.toSet());
            collectionSaved.setCollectionProducts(updatedCollectionProducts);
            return Optional.of(collectionSaved);
        } else {
            return Optional.empty();
        }
    }

  public Optional<Collection> updateCollectionIndex(Long id, Integer index) {
    Optional<Collection> optionalCollection = collectionRepository.findById(id);
    if (optionalCollection.isPresent()) {
      Collection collection = optionalCollection.get();
      collection.setIndex(index);
      return Optional.of(collectionRepository.save(collection));
    } else {
      return Optional.empty();
    }
  }

    // Delete a Collection
    public boolean deleteCollection(Long id) {
        if (collectionRepository.existsById(id)) {
            collectionRepository.deleteById(id);
            return true;
        }
        return false;
    }





}
