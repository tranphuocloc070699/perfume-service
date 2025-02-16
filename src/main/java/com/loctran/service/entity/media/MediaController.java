package com.loctran.service.entity.media;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.exception.custom.BadRequestException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import com.loctran.service.utils.R2Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * API for managing media files.
 * Provides endpoints for retrieving, uploading, and deleting media files.
 */
@Tag(name = "Media", description = "APIs for managing media files")
@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {
  private final MediaService mediaService;
private final R2Service r2Service;
  /**
   * Retrieves all media files.
   */
  @Operation(summary = "Get all media", description = "Retrieves all available media files")
  @GetMapping("")
  public ResponseEntity<ResponseDto> findAllMedia() {
    List<Media> medias = mediaService.findAll();
    return ResponseEntity.ok(ResponseDto.getDataSuccess(medias));
  }

  /**
   * Uploads a media file.
   */
  @Operation(
      summary = "Upload a media file",
      description = "Uploads a media file and saves it to the database"
  )
  @PostMapping(value = "", consumes = "multipart/form-data")
  public ResponseEntity<ResponseDto> uploadMedia(
      @RequestParam("entityType") EntityType entityType,
      @RequestPart("files") List<MultipartFile>  multipartFile
  ) {
      List<Media> mediaList = new ArrayList<>();
      multipartFile.forEach(file -> {
        try {
          String path = r2Service.uploadFile(entityType, file);
          Media media = mediaService.create(
              Media.builder().path(path).entityType(entityType).type(MediaType.IMAGE).build()
          );
          mediaList.add(media);
        }catch (IOException e){
          throw new BadRequestException(ResponseMessage.MEDIA_BAD_REQUEST);
        }
      });
      return ResponseEntity.ok(ResponseDto.createDataSuccess(mediaList));
    }


  /**
   * Update a media file by its ID.
   */
  @Operation(summary = "Update a media file", description = "Update a media file by its unique identifier")
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateMedia(@PathVariable Long id) {
    Media media = mediaService.findById(id);
    r2Service.deleteFile(media.getPath());
    mediaService.deleteById(id);
    return ResponseEntity.ok(ResponseDto.deleteDataSuccess(media));
  }

  /**
   * Deletes a media file by its ID.
   */
  @Operation(summary = "Delete a media file", description = "Deletes a media file by its unique identifier")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteMedia(@PathVariable Long id) {
    Media media = mediaService.findById(id);
    r2Service.deleteFile(R2Util.extractPath(media.getPath()));
    mediaService.deleteById(id);
    return ResponseEntity.ok(ResponseDto.deleteDataSuccess(media));
  }

}
