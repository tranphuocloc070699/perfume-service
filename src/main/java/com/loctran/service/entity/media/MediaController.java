package com.loctran.service.entity.media;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.media.dto.MediaDto;
import com.loctran.service.entity.media.dto.UpsaveMediaDto;
import com.loctran.service.entity.product.dto.ListProductDto;
import com.loctran.service.utils.FileUploadUtil;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {

  private final MediaService mediaService;
private final R2Service r2Service;
  @GetMapping("")
  public ResponseEntity<ResponseDto> findAllMedia() {
    List<Media> mediaList = mediaService.findAll();
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Lấy tất cả hình ảnh thành công");
    responseDto.setStatus(200);
    responseDto.setData(mediaList);
    return ResponseEntity.ok(responseDto);
  }

  @PostMapping("")
  public ResponseEntity<ResponseDto> saveMedia(@RequestParam("uploadDir") String uploadDir ,@RequestParam("image") MultipartFile multipartFile) {
    try {
        String path = r2Service.uploadFile(uploadDir,multipartFile);
            ResponseDto responseDto = ResponseDto.builder()
          .message("Upload file thành công").status(200).data(path).build();
      Media media = new Media();
      media.setPath(path);
      media.setType(MediaType.IMAGE);
      mediaService.create(media);
      
      return ResponseEntity.ok(responseDto);

    } catch (RuntimeException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deleteMedia(@PathVariable Long id) {
    Media media = mediaService.delete(id);
    ResponseDto responseDto = ResponseDto.builder().build();
    responseDto.setMessage("Xóa hình ảnh thành công");
    responseDto.setStatus(200);
    responseDto.setData(media);
    return ResponseEntity.ok(responseDto);
  }
}
