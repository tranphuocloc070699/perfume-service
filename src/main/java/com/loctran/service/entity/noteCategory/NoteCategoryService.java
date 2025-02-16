package com.loctran.service.entity.noteCategory;

import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteCategoryService {
  private final NoteCategoryRepository noteCategoryRepository;
  private final ModelMapper modelMapper;

  public List<NoteCategory> getAll() {
    return noteCategoryRepository.findAll();
  }

  public NoteCategory getById(Long id) {
    return noteCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
  }

  public NoteCategory create(UpsaveNoteCategoryDto dto) {
            NoteCategory noteCategory = modelMapper.map(dto,NoteCategory.class);

    return noteCategoryRepository.save(noteCategory);
  }

  public NoteCategory updateById(Long id, UpsaveNoteCategoryDto dto) {
    NoteCategory noteCategory = noteCategoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));



    noteCategory.setTitle(dto.getTitle());
    noteCategory.setDescription(dto.getDescription());
//    noteCategory.setNotes(noteCategoryDetails.getNotes());

    return noteCategoryRepository.save(noteCategory);
  }

  public void deleteById(Long id) {
    NoteCategory noteCategory = noteCategoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    noteCategoryRepository.delete(noteCategory);
  }
}