package com.loctran.service.entity.productNote;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductNoteRepository extends JpaRepository<ProductNote,Long> {
  Optional<ProductNote> findBySlug(String slug);
}