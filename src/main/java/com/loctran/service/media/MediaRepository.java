package com.loctran.service.media;

import com.loctran.service.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media,Integer> {

}