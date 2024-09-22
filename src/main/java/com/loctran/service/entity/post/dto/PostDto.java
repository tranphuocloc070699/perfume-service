package com.loctran.service.entity.post.dto;

import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.media.dto.MediaDto;
import com.loctran.service.entity.user.User;
import com.loctran.service.entity.user.dto.UserDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class PostDto {
  private Long id;
  private String title;
  private String excerpt;
  private String content;
  private Boolean isPinned;
  private MediaDto thumbnail;
  private UserDto user;
  private Date createdAt;
  private Date updatedAt;
}
