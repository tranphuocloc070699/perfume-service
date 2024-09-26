package com.loctran.service.entity.productCompare;

import com.loctran.service.entity.productNote.ProductNote;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCompareRepository extends JpaRepository<ProductCompare,Long> {
  Page<ProductCompare> findByProductOriginalId(Long productOriginalId, Pageable pageable);
}