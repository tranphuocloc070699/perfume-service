package com.loctran.service.product;

import com.loctran.service.media.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}