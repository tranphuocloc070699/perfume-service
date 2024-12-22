package com.loctran.service.entity.media;

import com.loctran.service.entity.media.dto.MediaDto;
import com.loctran.service.entity.media.dto.UpsaveMediaDto;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaService {
  private final MediaRepository mediaRepository;
  private final R2Service r2Service;

  public List<Media> findAll() {
    return mediaRepository.findAll();
  }

  public Media findById(Long id) {
    return mediaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Media", "id", id.toString()));
  }

  public Media create(Media media) {

    return mediaRepository.save(media);
  }

  public Media delete (Long id) {
    Media media = findById(id);
    mediaRepository.delete(media);
    return media;
  }


}
