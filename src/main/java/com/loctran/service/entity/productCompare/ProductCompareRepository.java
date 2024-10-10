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
  @Query(value = "SELECT \n" +
          "    pc.id, \n" +
          "    pc.created_at, \n" +
          "    pc.updated_at, \n" +
          "    p.id AS product_id, \n" +
          "    p.name,\n" +
          "    p.thumbnail, \n" +
          "    (SUM(COALESCE(cpv.compare_votes, 0)) + SUM(COALESCE(ov.original_votes, 0))) AS total_votes_sum -- Sum of compare votes and original votes\n" +
          "FROM public.tbl_product_compare pc\n" +
          "LEFT JOIN public.tbl_product p ON pc.product_compare_id = p.id\n" +
          "LEFT JOIN public.product_compare_compare_votes cpv ON pc.id = cpv.product_compare_id\n" +
          "LEFT JOIN public.product_compare_original_votes ov ON pc.id = ov.product_compare_id\n" +
          "WHERE pc.product_original_id = :id\n" +
          "GROUP BY \n" +
          "    pc.id, \n" +
          "    pc.created_at, \n" +
          "    pc.updated_at, \n" +
          "    p.id, \n" +
          "    p.thumbnail, \n" +
          "    p.name;",nativeQuery = true)
  List<Object[]> findProductCompare(@Param("id") Long id);

  Page<ProductCompare> findByProductOriginalId(Long productOriginalId, Pageable pageable);

  @Query("SELECT pc.productOriginal.id,pc.productOriginal.name,pc.productOriginal.thumbnail, " +
          "pc.productCompare.id,pc.productCompare.name,pc.productCompare.thumbnail " +
          "FROM ProductCompare pc WHERE pc.id = :id")
  List<Object[]> findByProductCompareId(@Param("id") Long id);


}