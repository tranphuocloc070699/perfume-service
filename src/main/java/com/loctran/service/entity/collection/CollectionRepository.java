package com.loctran.service.entity.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection,Long> {

    @Query("SELECT c FROM Collection c LEFT JOIN FETCH c.collectionProducts")
    List<Collection> findAllWithProducts();
}
