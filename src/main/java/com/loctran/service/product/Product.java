package com.loctran.service.product;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.loctran.service.media.Media;
import com.loctran.service.user.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "tbl_product"
)
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "id")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id")
  private Long id;

  @Column
  private String name;

  @Column(unique = true)
  private String slug;

  @Column
  private String description;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "thumbnail_id", referencedColumnName = "id")
  private Media thumbnail;


@OneToMany(fetch = FetchType.LAZY)

  private List<Media> galleries;

@OneToMany(fetch = FetchType.LAZY)
//  @Formula("SELECT m.id FROM tbl_media m WHERE m.product_id = id AND m.type = 'PRODUCT_OUTFIT'")
  private List<Media> outfits;

  private Integer dateReleased;

  @CreationTimestamp
  @Column(name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

}


