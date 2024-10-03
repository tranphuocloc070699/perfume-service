package com.loctran.service.entity.year;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface YearRepository extends JpaRepository<Year,Long> {
    Optional<Year> findByValue(Integer value);
}
