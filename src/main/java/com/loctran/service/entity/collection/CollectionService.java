package com.loctran.service.entity.collection;

import com.loctran.service.entity.collection.dto.UpsaveCollectionDto;
import com.loctran.service.entity.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final ProductRepository productRepository;
    private final CollectionProductRepository collectionProductRepository;


    public List<Collection> getAll(){
        List<Collection> collections = collectionRepository.findAll();

        for (Collection collection : collections) {
            List<CollectionProduct> products = collectionProductRepository.findByCollectionId(collection.getId());
            collection.setCollectionProducts(new HashSet<>(products));
        }

        return collections;
    }



    public Collection save(UpsaveCollectionDto upsaveCollectionDto) {
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
                            new IllegalArgumentException("Product not found with ID: " + productDto.getProductId())));
                    collectionProduct.setCollection(collectionSaved);
                    return  collectionProductRepository.save(collectionProduct);
                }).collect(Collectors.toSet());
        collectionSaved.setCollectionProducts(collectionProducts);
 return collectionSaved;
    }


    public Optional<Collection> updateCollection(Long id, UpsaveCollectionDto upsaveCollectionDto) {
        Optional<Collection> optionalCollection = collectionRepository.findById(id);
        if (optionalCollection.isPresent()) {
            Collection collection = optionalCollection.get();
            collection.setIcon(upsaveCollectionDto.getIcon());
            collection.setTitle(upsaveCollectionDto.getTitle());
            Collection collectionSaved = collectionRepository.save(collection);

            //Delete all collection product with collection id
            collectionProductRepository.deleteByCollectionId(collectionSaved.getId());

            Set<CollectionProduct> updatedCollectionProducts = upsaveCollectionDto.getCollectionProducts().stream()
                    .map(productDto -> {
                        CollectionProduct collectionProduct = new CollectionProduct();
                        collectionProduct.setIndex(productDto.getIndex());
                        collectionProduct.setProduct(productRepository.findById(productDto.getProductId()).orElseThrow(() ->
                                new IllegalArgumentException("Product not found with ID: " + productDto.getProductId())));
                        collectionProduct.setCollection(collectionSaved);
                        return  collectionProductRepository.save(collectionProduct);
                    }).collect(Collectors.toSet());

            collectionSaved.getCollectionProducts().clear();
            collectionSaved.getCollectionProducts().addAll(updatedCollectionProducts);

            return Optional.of(collectionSaved);
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
