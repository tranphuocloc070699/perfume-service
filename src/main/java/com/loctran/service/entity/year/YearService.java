package com.loctran.service.entity.year;


import com.loctran.service.entity.year.dto.CreateYearDto;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YearService {
    private final YearRepository yearRepository;

    public List<Year> getAllYear(){

        return yearRepository.findAllByOrderByValueDesc();
    }

    public Year findById(Long id){
        return yearRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
            ResponseMessage.DATA_NOT_FOUND));
    }

    public Year findByValue(Integer value){
        return yearRepository.findByValue(value).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    }



    public Year createYear(CreateYearDto dto){
            Year year = Year.builder().value(dto.getValue()).build();
            return yearRepository.save(year);
    }
}
