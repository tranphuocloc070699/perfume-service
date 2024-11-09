package com.loctran.service.entity.post;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.comment.Comment;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.media.MediaType;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.user.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    name = "tbl_post"
)
@JsonIdentityInfo(scope = Post.class,generator = PropertyGenerator.class, property = "id")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column
  private String title;

  @Column
  private String excerpt;

  @Column
  private String slug;

  @Column
  private String content;

  @Column
  private Boolean isPinned;

  @Column
  private String thumbnail;


  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "varchar(50) default 'NEWS'")
  private PostType type ;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "post_votes", joinColumns = @JoinColumn(name = "post_id"))
  @Column(name = "vote_post_id")
  private List<Long> votes = new ArrayList<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
  private List<Comment> comments;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;

  @CreationTimestamp
  @Column(name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;
}
