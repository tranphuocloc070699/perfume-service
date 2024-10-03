package com.loctran.service.entity.year;


import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.year.dto.CreateYearDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("year")
public class YearController {
    private final YearService yearService;

    @GetMapping("")
    public ResponseEntity<ResponseDto> getAllYear(){
        List<Year> years = yearService.getAllYear();
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy thông tin thành công");
        responseDto.setStatus(200);
        responseDto.setData(years);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> createYear(@RequestBody CreateYearDto dto){
        Year year = yearService.createYear(dto);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Thêm năm thành công");
        responseDto.setStatus(200);
        responseDto.setData(year);
        return ResponseEntity.ok(responseDto);
    }


}
