package com.loctran.service.entity.book;

import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
private final BookRepository bookRepository;
private final ModelMapper modelMapper;


  public List<Book> getAll(){
    return bookRepository.findAll();
  }

  public Book findById (Long id){
    return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
        ResponseMessage.DATA_NOT_FOUND));
  }


  public Book create( UpsaveBookDto dto){
    Book book = modelMapper.map(dto,Book.class);
    return bookRepository.save(book);
  }

  public Book updateById( Long id,UpsaveBookDto dto){
    Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    modelMapper.typeMap(UpsaveBookDto.class, Book.class)
        .addMappings(mapper -> mapper.skip(Book::setId));
    modelMapper.map(dto, book);
    return bookRepository.save(book);
  }

  public Book deleteById(Long id){
    Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    bookRepository.delete(book);
    return book;
  }
}
