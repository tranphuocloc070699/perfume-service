package com.loctran.service.entity.comment;

import com.loctran.service.entity.country.Country;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

  List<Comment> findAllByUserId(Long userId);
}