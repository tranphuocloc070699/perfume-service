package com.loctran.service.entity.productPrice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductPriceRepository extends JpaRepository<ProductPrice,Long> {

    List<ProductPrice> findByProductId(@Param("id") Long id);
}
