package com.loctran.service.entity.product;
import com.loctran.service.entity.product.dto.ListProductDto;
import com.loctran.service.entity.productPrice.LabelType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p.id FROM Product p ORDER BY p.id ASC")
    List<Long> findAllIds();

    @Query("SELECT DISTINCT new com.loctran.service.entity.product.dto.ProductDetailDto(p.id, p.name, p.slug, p.description, p.thumbnail,p.galleries,null, "
        + "new com.loctran.service.entity.year.dto.YearDto(null, null), " // Date released
        + "null, " // Product compare
        + "null, " // Brand
        + "null, " // Country
        + "null, " // Comments
        + "null, " // Notes
        + "null, " // Votes
        + "p.createdAt, p.updatedAt) "
        + "FROM Product p "
        + "WHERE p.id = :id")
    Optional<List<Object>> findProductDetailById(@Param("id") Long id);

    @Query("SELECT DISTINCT new com.loctran.service.entity.product.dto.ListProductDto(p.id, p.name, p.slug, p.description, p.thumbnail, "
        + "new com.loctran.service.entity.year.dto.YearDto(y.id, y.value), "
        + "null,"
        + " p.createdAt, p.updatedAt) "
        + "FROM Product p "
        + "LEFT JOIN p.country c "
        + "LEFT JOIN p.brand b "
        + "LEFT JOIN p.dateReleased y "
        + "LEFT JOIN p.prices pr " // Join prices
        + "WHERE (:brandId IS NULL OR b.id = :brandId) "
        + "AND (:countryId IS NULL OR c.id = :countryId) "
//        + "AND (:notesIds IS NULL OR tn.id IN :notesIds OR mn.id IN :notesIds OR bn.id IN :notesIds) "
        + "AND (:productName IS NULL OR p.name LIKE %:productName%) ")
    Page<ListProductDto> findAllProducts(Pageable pageable,
        @Param("brandId") Long brandId,
        @Param("countryId") Long countryId,
//        @Param("notesIds") List<Long> notesIds,
        @Param("productName") String productName
        );




}