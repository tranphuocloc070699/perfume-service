package com.loctran.service.entity.collection;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.brand.dto.CreateBrandDto;
import com.loctran.service.entity.collection.dto.CollectionDto;
import com.loctran.service.entity.collection.dto.UpdateCollectIndexDto;
import com.loctran.service.entity.collection.dto.UpsaveCollectionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("collection")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;





    @GetMapping("")
    public ResponseEntity<ResponseDto> getAll() {
      try {

        List<CollectionDto> collections = collectionService.getAll();

        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy thông tin thành công");
        responseDto.setStatus(200);
        responseDto.setData(collections);
        return ResponseEntity.ok(responseDto);
      } catch (Exception e) {
          System.out.println("Error: " + e.getMessage());
          ResponseDto responseDto = ResponseDto.builder().build();
          responseDto.setMessage("Process failure");
          responseDto.setStatus(500);
          responseDto.setErrors(e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
      }
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> createCollection(@RequestBody UpsaveCollectionDto dto) {
        System.out.println("Dto: " + dto.getCollectionProducts().size());
        Collection collection = collectionService.save(dto);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Tạo Collection thành công");
        responseDto.setStatus(200);
        responseDto.setData(collection);
        return ResponseEntity.ok(responseDto);
    }


    @PutMapping("/index")
    public ResponseEntity<ResponseDto> updateCollectionIndexes(@RequestBody List<UpdateCollectIndexDto> updateCollectIndexDtos) {
        try {
            for (UpdateCollectIndexDto dto : updateCollectIndexDtos) {
                Optional<Collection> optionalCollection = collectionService.updateCollectionIndex(dto.getCollectionId(), dto.getIndex());
                if (optionalCollection.isEmpty()) {
                    throw new IllegalArgumentException("Collection not found with ID: " + dto.getCollectionId());
                }
            }
            ResponseDto responseDto = ResponseDto.builder().build();
            responseDto.setMessage("Updated collection indexes successfully");
            responseDto.setStatus(200);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            ResponseDto responseDto = ResponseDto.builder().build();
            responseDto.setMessage("Process failure");
            responseDto.setStatus(500);
            responseDto.setErrors(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }


    // Update a Collection
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateCollection(@PathVariable Long id, @RequestBody UpsaveCollectionDto upsaveCollectionDto) {
        Optional<Collection> updatedCollection = collectionService.updateCollection(id, upsaveCollectionDto);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Chỉnh sửa Collection thành công");
        responseDto.setStatus(200);
        responseDto.setData(updatedCollection);
        return ResponseEntity.ok(responseDto);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCollection(@PathVariable Long id) {
        String message;
        Integer status;

        if (collectionService.deleteCollection(id)) {
            message = "Xóa Collection thành công";
            status = HttpStatus.OK.value();
        } else {
            message = "Xóa Collection thất bại";
            status = HttpStatus.BAD_REQUEST.value();
        }

        ResponseDto responseDto = ResponseDto.builder()
                .status(status)
                .message(message)
                .build();

        return new ResponseEntity<>(responseDto, HttpStatus.valueOf(status));
    }

}
