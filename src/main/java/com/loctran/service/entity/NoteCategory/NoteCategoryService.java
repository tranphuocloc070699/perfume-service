package com.loctran.service.entity.NoteCategory;

import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteCategoryService {
  private final NoteCategoryRepository noteCategoryRepository;

  public List<NoteCategory> getAllNoteCategories() {
    return noteCategoryRepository.findAll();
  }

  public Optional<NoteCategory> getNoteCategoryById(Long id) {
    return noteCategoryRepository.findById(id);
  }

  public NoteCategory createNoteCategory(NoteCategory noteCategory) {
    return noteCategoryRepository.save(noteCategory);
  }

  public NoteCategory updateNoteCategory(Long id, NoteCategory noteCategoryDetails) {
    NoteCategory noteCategory = noteCategoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));

    noteCategory.setTitle(noteCategoryDetails.getTitle());
    noteCategory.setDescription(noteCategoryDetails.getDescription());
    noteCategory.setNotes(noteCategoryDetails.getNotes());

    return noteCategoryRepository.save(noteCategory);
  }

  public void deleteNoteCategory(Long id) {
    NoteCategory noteCategory = noteCategoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    noteCategoryRepository.delete(noteCategory);
  }
}