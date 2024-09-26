package com.loctran.service.entity.comment;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.post.Post;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.productCompare.ProductCompare;
import com.loctran.service.entity.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "tbl_comment"
)
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "id")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column
  private String content;

  @ManyToOne(cascade = CascadeType.ALL)
  private User user;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL)
  private Product product;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL)
  private ProductCompare productCompare;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL)
  private Post post;

  @CreationTimestamp
  @Column(name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;
}
