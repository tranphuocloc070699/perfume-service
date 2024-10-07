package com.loctran.service.entity.post.dto;

import com.loctran.service.entity.user.dto.UserDto;

import java.util.Date;

public class PostDto {
  private Long id;
  private String title;
  private String excerpt;
  private String content;
  private Boolean isPinned;
  private String thumbnail;
  private UserDto user;
  private Date createdAt;
  private Date updatedAt;
}
