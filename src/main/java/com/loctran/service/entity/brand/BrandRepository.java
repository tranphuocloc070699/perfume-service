package com.loctran.service.entity.brand;

import com.loctran.service.entity.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand,Long> {

    @Query("SELECT b.id,b.name,b.description,b.homepageLink,b.thumbnail FROM Brand b WHERE b.id = :id")
    List<Object[]> findBrandById(@Param("id") Long id);
}