package com.loctran.service.entity.media;

import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaService {
  private final MediaRepository mediaRepository;

  public List<Media> findAll() {
    return mediaRepository.findAll();
  }

  public Media findById(Long id) {
    return mediaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
        ResponseMessage.DATA_NOT_FOUND));
  }

  public Media create(Media media) {
    return mediaRepository.save(media);
  }

  public Media updateEntityId(Long mediaId, Long entityId){
    Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    media.setEntityId(entityId);
    media = mediaRepository.save(media);
    return media;
  }

  public Media deleteById(Long id) {
    Media media = findById(id);
    mediaRepository.delete(media);
    return media;
  }


}
