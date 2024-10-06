package com.loctran.service.entity.product;

import com.loctran.service.entity.product.dto.ListProductDto;

import com.loctran.service.entity.product.dto.ProductDetailDto;
import java.util.List;

import com.loctran.service.entity.product.dto.ProductId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT DISTINCT new com.loctran.service.entity.product.dto.ListProductDto(p.id, p.name, p.slug, p.description, " +
        "new com.loctran.service.entity.media.dto.MediaDto(m.id, m.path, m.type), new com.loctran.service.entity.year.dto.YearDto(y.id, y.value), p.createdAt, p.updatedAt) " +
        "FROM Product p JOIN p.thumbnail m " +
        "LEFT JOIN p.country c " +
        "LEFT JOIN p.brand b " +
        "LEFT JOIN p.notes n " +
        "LEFT JOIN p.dateReleased y " +
        "WHERE (:brandId IS NULL OR b.id = :brandId) " +     
        "AND (:countryId IS NULL OR c.id = :countryId) " +
        "AND (:notesIds IS NULL OR n.id IN :notesIds)")
    Page<ListProductDto> findAllProducts(Pageable pageable,
        @Param("brandId") Long brandId,
        @Param("countryId") Long countryId,
        @Param("notesIds") List<Long> notesIds);





    @Query("SELECT p.id FROM Product p ORDER BY p.id ASC")
    List<Long> findAllIds();

//    @Query("SELECT new com.loctran.service.entity.product.dto.ProductDetailDto(p.id, p.name, p.slug, p.description, "
//        + "(SELECT new com.loctran.service.entity.media.dto.MediaDto(m.id, m.path, m.type) FROM p.mediaList m WHERE m.type = com.loctran.service.entity.media.MediaType.PRODUCT_THUMBNAIL), " // Thumbnail
//        + "(SELECT new com.loctran.service.entity.media.dto.MediaDto(m.id, m.path, m.type) FROM p.mediaList m WHERE m.type = com.loctran.service.entity.media.MediaType.PRODUCT_GALLERY), " // Galleries
//        + "(SELECT new com.loctran.service.entity.media.dto.MediaDto(m.id, m.path, m.type) FROM p.mediaList m WHERE m.type = com.loctran.service.entity.media.MediaType.PRODUCT_OUTFIT), " // Outfits
//        + "new com.loctran.service.entity.year.dto.YearDto(y.id, y.value), " // Date released
//        + "(SELECT new com.loctran.service.entity.productCompare.dto.ProductCompareDto(pc.id, null, null, null, null, null, pc.createdAt, pc.updatedAt)), " // Product compare
//        + "new com.loctran.service.entity.brand.dto.BrandDto(b.id, b.name, b.description, b.homepageLink, new com.loctran.service.entity.media.dto.MediaDto(bt.id, bt.path, bt.type), b.createdAt, b.updatedAt), " // Brand
//        + "new com.loctran.service.entity.country.dto.CountryDto(c.id, c.name, c.code, new com.loctran.service.entity.media.dto.MediaDto(ct.id, ct.path, ct.type), c.createdAt, c.updatedAt), " // Country
//        + "(SELECT new com.loctran.service.entity.comment.dto.CommentDto(cm.id, cm.content, new com.loctran.service.entity.user.dto.UserDto(ucm.id, new com.loctran.service.entity.media.dto.MediaDto(ua.id, ua.path, ua.type) ,ucm.name) , c.createdAt, c.updatedAt) FROM p.comments cm), " // Comments
//        + "(SELECT new com.loctran.service.entity.productNote.dto.ProductNoteDto(n.id, n.name, n.slug, new com.loctran.service.entity.media.dto.MediaDto(nt.id, nt.path, nt.type)) FROM p.notes n LEFT JOIN n.thumbnail nt), " // Notes
//        + "(SELECT new com.loctran.service.entity.user.dto.UserDto(v.id, null,v.name) FROM p.votes v), " // Votes
//        + "p.createdAt, p.updatedAt) "
//        + "FROM Product p "
//        + "LEFT JOIN p.thumbnail m "
//        + "LEFT JOIN p.dateReleased y "
//        + "LEFT JOIN p.originalComparisons pc "
//        + "LEFT JOIN p.brand b "
//        + "LEFT JOIN b.thumbnail bt "
//        + "LEFT JOIN p.country c "
//        + "LEFT JOIN c.thumbnail ct "
//        + "LEFT JOIN p.comments cm "
//        + "LEFT JOIN cm.user ucm "
//        + "LEFT JOIN ucm.avatar ua "
//        + "WHERE p.id = :id")
//    ProductDetailDto findProductDetailById(@Param("id") Long id);

    @Query("SELECT new com.loctran.service.entity.product.dto.ProductDetailDto(p.id, p.name, p.slug, p.description, "
        + "(SELECT new com.loctran.service.entity.media.dto.MediaDto(m.id, m.path, m.type) FROM p.mediaList m WHERE m.type = com.loctran.service.entity.media.MediaType.PRODUCT_THUMBNAIL), " // Thumbnail
        + "(SELECT new com.loctran.service.entity.media.dto.MediaDto(m.id, m.path, m.type) FROM p.mediaList m WHERE m.type = com.loctran.service.entity.media.MediaType.PRODUCT_GALLERY), " // Galleries
        + "(SELECT new com.loctran.service.entity.media.dto.MediaDto(m.id, m.path, m.type) FROM p.mediaList m WHERE m.type = com.loctran.service.entity.media.MediaType.PRODUCT_OUTFIT), " // Outfits
        + "new com.loctran.service.entity.year.dto.YearDto(y.id, y.value), " // Date released
        + "(SELECT new com.loctran.service.entity.productCompare.dto.ProductCompareDto(pc.id, null, null, null, null, null, pc.createdAt, pc.updatedAt)), " // Product compare
        + "new com.loctran.service.entity.brand.dto.BrandDto(b.id, b.name, b.description, b.homepageLink, new com.loctran.service.entity.media.dto.MediaDto(bt.id, bt.path, bt.type), b.createdAt, b.updatedAt), " // Brand
        + "new com.loctran.service.entity.country.dto.CountryDto(c.id, c.name, c.code, new com.loctran.service.entity.media.dto.MediaDto(ct.id, ct.path, ct.type), c.createdAt, c.updatedAt), " // Country
        + "(SELECT new com.loctran.service.entity.comment.dto.CommentDto(cm.id, cm.content, new com.loctran.service.entity.user.dto.UserDto(ucm.id, new com.loctran.service.entity.media.dto.MediaDto(ua.id, ua.path, ua.type) ,ucm.name) , c.createdAt, c.updatedAt) FROM p.comments cm), " // Comments
        + "(SELECT new com.loctran.service.entity.productNote.dto.ProductNoteDto(n.id, n.name, n.slug, new com.loctran.service.entity.media.dto.MediaDto(nt.id, nt.path, nt.type)) FROM p.notes n LEFT JOIN n.thumbnail nt), " // Notes
        + "(SELECT new com.loctran.service.entity.user.dto.UserDto(v.id, null,v.name) FROM p.votes v), " // Votes
        + "p.createdAt, p.updatedAt) "
        + "FROM Product p "
        + "LEFT JOIN p.thumbnail m "
        + "LEFT JOIN p.dateReleased y "
        + "LEFT JOIN p.originalComparisons pc "
        + "LEFT JOIN p.brand b "
        + "LEFT JOIN b.thumbnail bt "
        + "LEFT JOIN p.country c "
        + "LEFT JOIN c.thumbnail ct "
        + "LEFT JOIN p.comments cm "
        + "LEFT JOIN cm.user ucm "
        + "LEFT JOIN ucm.avatar ua "
        + "WHERE p.id = :id")
    ProductDetailDto findProductDetailById(@Param("id") Long id);

}