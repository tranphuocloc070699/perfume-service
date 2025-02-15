package com.loctran.service.entity.comment;


import com.loctran.service.entity.answer.Answer;
import com.loctran.service.entity.answer.AnswerRepository;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.brand.BrandService;
import com.loctran.service.entity.comment.dto.CreateCommentDto;
import com.loctran.service.entity.comment.dto.UpdateCommentDto;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.country.CountryRepository;
import com.loctran.service.entity.country.dto.CreateCountryDto;
import com.loctran.service.entity.country.dto.UpdateCountryDto;
import com.loctran.service.entity.post.Post;
import com.loctran.service.entity.post.PostRepository;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.product.ProductRepository;
import com.loctran.service.entity.productCompare.ProductCompare;
import com.loctran.service.entity.productCompare.ProductCompareRepository;
import com.loctran.service.entity.user.User;
import com.loctran.service.entity.user.UserRepository;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;
  private final PostRepository postRepository;
  private final ProductCompareRepository productCompareRepository;
  private final AnswerRepository answerRepository;

  public List<Comment> findAll(Long userId) {
    return commentRepository.findAllByUserId(userId);
  }

  public List<Comment> findAllByAnswerId(Long answerId) {
    return commentRepository.findAllByAnswerId(answerId);
  }



  public Comment findById(Long id) {
    return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
        ResponseMessage.DATA_NOT_FOUND));
  }

  public Comment saveProductComment(Long userId, Long productId, CreateCommentDto dto) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.USER_NOT_FOUND));
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.PRODUCT_NOT_FOUND));
    Comment comment = Comment.builder()
        .content(dto.getContent())
        .user(user)
        .product(product)
        .build();
    return commentRepository.save(comment);
  }

  public Comment savePostComment(Long userId, Long postId, CreateCommentDto dto) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.USER_NOT_FOUND));
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.POST_NOT_FOUND));
    Comment comment = Comment.builder()
        .content(dto.getContent())
        .user(user)
        .post(post)
        .build();
    return commentRepository.save(comment);
  }

  public Comment saveProductCompareComment(Long userId, Long productCompareId, CreateCommentDto dto) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.USER_NOT_FOUND));
    ProductCompare productCompare = productCompareRepository.findById(productCompareId)
        .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.POST_NOT_FOUND));
    Comment comment = Comment.builder()
        .content(dto.getContent())
        .user(user)
        .productCompare(productCompare)
        .build();
    return commentRepository.save(comment);
  }

  public Comment update(Long commentId, UpdateCommentDto dto) {
    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    comment.setContent(dto.getContent());
    return commentRepository.save(comment);
  }

  public Comment delete(Long commentId) {
    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
    commentRepository.delete(comment);
    return comment;
  }

    public Comment saveAnswerComment(Long userId, Long answerId, CreateCommentDto createCommentDto) {
      User user = userRepository.findById(userId)
              .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.USER_NOT_FOUND));
      Answer answer = answerRepository.findById(answerId)
              .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.DATA_NOT_FOUND));
      Comment comment = Comment.builder()
              .content(createCommentDto.getContent())
              .user(user)
              .answer(answer)
              .build();
      return commentRepository.save(comment);
    }
}
