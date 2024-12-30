package com.loctran.service.entity.color;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorService {
  private final ColorRepository colorRepository;

  public List<Color> getAllColors() {
    return colorRepository.findAll();
  }

  public Color getColorById(Long id) {
    return colorRepository.findById(id).orElseThrow(() -> new RuntimeException("Color not found"));
  }

  public Color createColor(Color color) {
    return colorRepository.save(color);
  }

  public Color updateColor(Long id, Color colorDetails) {
    Color color = colorRepository.findById(id).orElseThrow(() -> new RuntimeException("Color not found"));
    color.setName(colorDetails.getName());
    color.setHexCode(colorDetails.getHexCode());
    return colorRepository.save(color);
  }

  public void deleteColor(Long id) {
    Color color = colorRepository.findById(id).orElseThrow(() -> new RuntimeException("Color not found"));
    colorRepository.delete(color);
  }
}