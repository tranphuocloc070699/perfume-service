package com.loctran.service.entity.productCompare;

import com.loctran.service.entity.productNote.ProductNote;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductCompareRepository extends JpaRepository<ProductCompare,Long> {
  Page<ProductCompare> findByProductOriginalId(Long productOriginalId, Pageable pageable);


  @Query("SELECT p.id, p.createdAt, p.updatedAt, pc.id, pc.name, pc.thumbnail, ov, cv " +
          "FROM ProductCompare p " +
          "JOIN p.productCompare pc " +
          "LEFT JOIN p.originalVotes ov " +
          "LEFT JOIN p.compareVotes cv " +
          "WHERE p.productOriginal.id = :id")
  List<Object[]> findProductCompareWithOriginalProductAvatarAndId(@Param("id") Long id);
}