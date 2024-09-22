package com.loctran.service.entity.comment.dto;

import com.loctran.service.entity.media.dto.MediaDto;
import com.loctran.service.entity.user.dto.UserDto;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
  private Long id;
  private String content;
  private UserDto user;
  private Date createdAt;
  private Date updatedAt;
}
