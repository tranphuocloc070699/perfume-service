package com.loctran.service.entity.media.dto;

import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.media.MediaType;


public class UpsaveMediaDto {
  private String path;

  public Media mapToMedia(){
    return Media.builder().path(this.path).type(MediaType.IMAGE).build();
  }
}
