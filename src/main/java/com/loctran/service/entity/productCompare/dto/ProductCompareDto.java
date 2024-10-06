package com.loctran.service.entity.productCompare.dto;

import com.loctran.service.entity.comment.Comment;
import com.loctran.service.entity.comment.dto.CommentDto;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.product.dto.ProductDetailDto;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCompareDto {
  private Long id;
  private ProductDetailDto productOriginal;
  private ProductDetailDto productCompare;
  private List<Long> originalVotes;
  private List<Long> compareVotes;
  private List<CommentDto> comments;
  private Date createdAt;
  private Date updatedAt;
}
