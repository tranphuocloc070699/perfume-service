package com.loctran.service.entity.product;
import com.loctran.service.entity.product.dto.ListProductDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT DISTINCT new com.loctran.service.entity.product.dto.ListProductDto(p.id, p.name, p.slug, p.description, p.thumbnail, " +
            "new com.loctran.service.entity.year.dto.YearDto(y.id, y.value), p.createdAt, p.updatedAt) " +
            "FROM Product p " +
            "LEFT JOIN p.country c " +
            "LEFT JOIN p.brand b " +
            "LEFT JOIN p.topNotes tn " +  // Join top notes
            "LEFT JOIN p.middleNotes mn " +  // Join middle notes
            "LEFT JOIN p.baseNotes bn " +  // Join base notes
            "LEFT JOIN p.dateReleased y " +
            "WHERE (:brandId IS NULL OR b.id = :brandId) " +
            "AND (:countryId IS NULL OR c.id = :countryId) " +
            "AND (:notesIds IS NULL OR tn.id IN :notesIds OR mn.id IN :notesIds OR bn.id IN :notesIds)")
    Page<ListProductDto> findAllProducts(Pageable pageable,
        @Param("brandId") Long brandId,
        @Param("countryId") Long countryId,
        @Param("notesIds") List<Long> notesIds);





    @Query("SELECT p.id FROM Product p ORDER BY p.id ASC")
    List<Long> findAllIds();

//    @Query("SELECT new com.loctran.service.entity.product.dto.ProductDetailDto(p.id, p.name, p.slug, p.description, p.thumbnail, p.galleries, p.outfits, "
//        + "new com.loctran.service.entity.year.dto.YearDto(y.id, y.value), " // Date released
//        + "(SELECT new com.loctran.service.entity.productCompare.dto.ProductCompareDto(pc.id, null, null, null, null, null, pc.createdAt, pc.updatedAt)), " // Product compare
//        + "new com.loctran.service.entity.brand.dto.BrandDto(b.id, b.name, b.description, b.homepageLink,b.thumbnail , b.createdAt, b.updatedAt), " // Brand
//        + "new com.loctran.service.entity.country.dto.CountryDto(c.id, c.name, c.code, c.thumbnail , c.createdAt, c.updatedAt), " // Country
//        + "(SELECT new com.loctran.service.entity.comment.dto.CommentDto(cm.id, cm.content, new com.loctran.service.entity.user.dto.UserDto(ucm.id, ucm.avatar ,ucm.name) , c.createdAt, c.updatedAt) FROM p.comments cm), " // Comments
//        + "(SELECT new com.loctran.service.entity.productNote.dto.ProductNoteDto(n.id, n.name, n.slug, n.thumbnail) FROM p.notes n), " // Notes
//        + "(SELECT new com.loctran.service.entity.user.dto.UserDto(v.id, v.avatar ,v.name) FROM p.votes v), " // Votes
//        + "p.createdAt, p.updatedAt) "
//        + "FROM Product p "
//        + "LEFT JOIN p.dateReleased y "
//        + "LEFT JOIN p.originalComparisons pc "
//        + "LEFT JOIN p.brand b "
//        + "LEFT JOIN p.country c "
//        + "LEFT JOIN p.comments cm "
//        + "LEFT JOIN cm.user ucm "
//        + "WHERE p.id = :id")
//    ProductDetailDto findProductDetailById(@Param("id") Long id);


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

}