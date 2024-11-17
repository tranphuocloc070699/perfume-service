package com.loctran.service.entity.book;

import com.loctran.service.entity.answer.Answer;
import com.loctran.service.entity.question.Question;
import com.loctran.service.entity.question.dto.QuestionDto;
import com.loctran.service.entity.user.User;
import com.loctran.service.exception.custom.ForbiddenException;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
private final BookRepository bookRepository;


  public List<Book> getAll(){
    return bookRepository.findAll();
  }

  public Book findById (Long id){
    return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book","id",id.toString()));
  }


  public Book create( BookDto dto){

    Book book = Book.builder().name(dto.getName()).description(dto.getDescription()).link(dto.getLink()).thumbnail(dto.getThumbnail()).build();


    return bookRepository.save(book);
  }

  public Book update( Long id,BookDto dto){
    Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book","id",id.toString()));
    book.setName(dto.getName());
    book.setDescription(dto.getDescription());
    book.setLink(dto.getLink());
    book.setThumbnail(dto.getThumbnail());
    return bookRepository.save(book);
  }

  public Book delete(Long id){
    Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book","id",id.toString()));
    bookRepository.delete(book);
    return book;
  }
}
