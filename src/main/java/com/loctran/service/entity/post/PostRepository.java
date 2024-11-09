package com.loctran.service.entity.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
