package com.loctran.service.entity.year;


import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.year.dto.CreateYearDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * API for managing years.
 * Provides endpoints for retrieving and creating years.
 */
@Tag(name = "Year", description = "APIs for managing years")
@RequiredArgsConstructor
@RestController
@RequestMapping("year")
public class YearController {
    private final YearService yearService;

    /**
     * Retrieves all years.
     */
    @Operation(summary = "Get all years", description = "Retrieves all available years")
    @GetMapping("")
    public ResponseEntity<ResponseDto> getAllYear() {
        List<Year> years = yearService.getAllYear();
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy thông tin thành công");
        responseDto.setStatus(200);
        responseDto.setData(years);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Creates a new year entry.
     */
    @Operation(summary = "Create a new year", description = "Creates a new year entry in the database")
    @PostMapping("")
    public ResponseEntity<ResponseDto> createYear(@RequestBody CreateYearDto dto) {
        Year year = yearService.createYear(dto);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Thêm năm thành công");
        responseDto.setStatus(200);
        responseDto.setData(year);
        return ResponseEntity.ok(responseDto);
    }

}
