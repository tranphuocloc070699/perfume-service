package com.loctran.service.entity.collection;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectionProductRepository extends JpaRepository<CollectionProduct,Long> {
    List<CollectionProduct> findByCollectionId(Long collectionId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CollectionProduct cp WHERE cp.collection.id = :collectionId")
    void deleteByCollectionId(@Param("collectionId") Long collectionId);
}
