package com.loctran.service.entity.collection;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionService {
    private final CollectionRepository collectionRepository;


    public Collection save(Collection collection) {
        return collectionRepository.save(collection);
    }





}
