package com.loctran.service.entity.year;


import com.loctran.service.entity.year.dto.CreateYearDto;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YearService {
    private final YearRepository yearRepository;

    public List<Year> getAllYear(){
        return yearRepository.findAll();
    }

    public Year findById(Long id){
        return yearRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Year","id",id.toString()));
    }

    public Year findByValue(Integer value){
        return yearRepository.findByValue(value).orElseThrow(() -> new ResourceNotFoundException("Year","value",value.toString()));
    }



    public Year createYear(CreateYearDto dto){
            Year year = Year.builder().value(dto.getValue()).build();
            return yearRepository.save(year);
    }
}
