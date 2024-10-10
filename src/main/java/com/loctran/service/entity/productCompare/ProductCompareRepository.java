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


  @Query(value = "SELECT p.id, p.created_at, p.updated_at " +
          "FROM tbl_product_compare p " +
          "WHERE p.product_original_id = :id",nativeQuery = true)
  List<Object> findProductCompareWithOriginalProductAvatarAndId(@Param("id") Long id);
}