package com.loctran.service.entity.collection;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.brand.dto.CreateBrandDto;
import com.loctran.service.entity.collection.dto.CollectionDto;
import com.loctran.service.entity.collection.dto.UpdateCollectIndexDto;
import com.loctran.service.entity.collection.dto.UpsaveCollectionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * API for managing collections.
 * Provides endpoints for retrieving, creating, updating, and deleting collections.
 */
@Tag(name = "Collection", description = "APIs for managing collections")
@RestController
@RequestMapping("collection")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    /**
     * Retrieves all collections.
     */
    @Operation(summary = "Get all collections", description = "Retrieves all available collections")
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
            ResponseDto responseDto = ResponseDto.builder().build();
            responseDto.setMessage("Process failure");
            responseDto.setStatus(500);
            responseDto.setErrors(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    /**
     * Creates a new collection.
     */
    @Operation(summary = "Create a collection", description = "Creates a new collection")
    @PostMapping("")
    public ResponseEntity<ResponseDto> createCollection(@RequestBody UpsaveCollectionDto dto) {
        Collection collection = collectionService.save(dto);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Tạo Collection thành công");
        responseDto.setStatus(200);
        responseDto.setData(collection);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Updates the indexes of multiple collections.
     */
    @Operation(summary = "Update collection indexes", description = "Updates the indexes of multiple collections")
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
            ResponseDto responseDto = ResponseDto.builder().build();
            responseDto.setMessage("Process failure");
            responseDto.setStatus(500);
            responseDto.setErrors(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

    /**
     * Updates an existing collection.
     */
    @Operation(summary = "Update a collection", description = "Updates an existing collection")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateCollection(@PathVariable Long id, @RequestBody UpsaveCollectionDto upsaveCollectionDto) {
        Optional<Collection> updatedCollection = collectionService.updateCollection(id, upsaveCollectionDto);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Chỉnh sửa Collection thành công");
        responseDto.setStatus(200);
        responseDto.setData(updatedCollection);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Deletes a collection by its ID.
     */
    @Operation(summary = "Delete a collection", description = "Deletes a collection by its unique identifier")
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
