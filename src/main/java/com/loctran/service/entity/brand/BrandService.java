package com.loctran.service.entity.brand;

import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {
  private final BrandRepository brandRepository;
  private final ModelMapper modelMapper;

  public List<Brand> findAll() {
    return brandRepository.findAll();
  }

  public Brand findById(Long id) {
    return brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
        ResponseMessage.DATA_NOT_FOUND));
  }

  public Brand create(UpsaveBrandDto dto) {
    Brand brand = modelMapper.map(dto,Brand.class);
    return brandRepository.save(brand);
  }

  public Brand updateById(Long id, UpsaveBrandDto dto) {
    Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    modelMapper.map(dto, brand);
    return brand;
  }

  public Brand deleteById(Long id) {
    Brand brand = brandRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    brandRepository.delete(brand);
    return brand;
  }
}
