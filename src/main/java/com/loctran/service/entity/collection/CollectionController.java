package com.loctran.service.entity.collection;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.collection.dto.CollectionDto;
import com.loctran.service.entity.collection.dto.UpdateCollectIndexDto;
import com.loctran.service.entity.collection.dto.UpsaveCollectionDto;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
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
            List<CollectionDto> collections = collectionService.getAll();
            return ResponseEntity.ok(ResponseDto.getDataSuccess(collections));
    }

    /**
     * Creates a new collection.
     */
    @Operation(summary = "Create a collection", description = "Creates a new collection")
    @PostMapping("")
    public ResponseEntity<ResponseDto> createCollection(@RequestBody UpsaveCollectionDto dto) {
        Collection collection = collectionService.create(dto);
        return ResponseEntity.ok(ResponseDto.getDataSuccess(collection));
    }

    /**
     * Updates the indexes of multiple collections.
     */
    @Operation(summary = "Update collection indexes", description = "Updates the indexes of multiple collections")
    @PutMapping("/index")
    public ResponseEntity<ResponseDto> updateCollectionIndexes(@RequestBody List<UpdateCollectIndexDto> updateCollectIndexDtos) {
            for (UpdateCollectIndexDto dto : updateCollectIndexDtos) {
                Optional<Collection> optionalCollection = collectionService.updateCollectionIndex(dto.getCollectionId(), dto.getIndex());
                if (optionalCollection.isEmpty()) {
                    throw new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND);
                }
            }
            ResponseDto responseDto = ResponseDto.builder().build();
            responseDto.setMessage(ResponseMessage.UPDATE_DATA_SUCCESS);
            responseDto.setStatus(200);
            return ResponseEntity.ok(responseDto);
    }

    /**
     * Updates an existing collection.
     */
    @Operation(summary = "Update a collection", description = "Updates an existing collection")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateCollection(@PathVariable Long id, @RequestBody UpsaveCollectionDto upsaveCollectionDto) {
        Optional<Collection> updatedCollection = collectionService.updateById(id, upsaveCollectionDto);
        return ResponseEntity.ok(ResponseDto.updateDataSuccess(updatedCollection));
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
            message = ResponseMessage.DELETE_DATA_SUCCESS;
            status = HttpStatus.OK.value();
        } else {
            message = ResponseMessage.DELETE_DATA_FORBIDDEN;
            status = HttpStatus.BAD_REQUEST.value();
        }

        ResponseDto responseDto = ResponseDto.builder()
            .status(status)
            .message(message)
            .build();

        return new ResponseEntity<>(responseDto, HttpStatus.valueOf(status));
    }

}
