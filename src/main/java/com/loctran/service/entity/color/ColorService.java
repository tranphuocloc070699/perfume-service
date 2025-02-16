package com.loctran.service.entity.color;

import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorService {
  private final ColorRepository colorRepository;

  public List<Color> getAll() {
    return colorRepository.findAll();
  }

  public Color getById(Long id) {
    return colorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
        ResponseMessage.DATA_NOT_FOUND));
  }

  public Color create(Color color) {
    return colorRepository.save(color);
  }

  public Color updateById(Long id, Color colorDetails) {
    Color color = colorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    color.setName(colorDetails.getName());
    color.setHexCode(colorDetails.getHexCode());
    return colorRepository.save(color);
  }

  public void deleteById(Long id) {
    Color color = colorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    colorRepository.delete(color);
  }
}