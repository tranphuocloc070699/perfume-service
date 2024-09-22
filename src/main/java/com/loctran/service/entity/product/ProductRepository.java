package com.loctran.service.entity.product;

import com.loctran.service.entity.product.dto.ListProductDto;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT DISTINCT new com.loctran.service.entity.product.dto.ListProductDto(p.id, p.name, p.slug, p.description, " +
        "new com.loctran.service.entity.media.dto.MediaDto(m.id, m.path, m.type), p.dateReleased, p.createdAt, p.updatedAt) " +
        "FROM Product p JOIN p.thumbnail m " +
        "LEFT JOIN p.country c " +
        "LEFT JOIN p.brand b " +
        "LEFT JOIN p.notes n " +
        "WHERE (:brandId IS NULL OR b.id = :brandId) " +
        "AND (:countryId IS NULL OR c.id = :countryId) " +
        "AND (:notesIds IS NULL OR n.id IN :notesIds)")
    Page<ListProductDto> findAllProducts(Pageable pageable,
        @Param("brandId") Long brandId,
        @Param("countryId") Long countryId,
        @Param("notesIds") List<Long> notesIds);

}