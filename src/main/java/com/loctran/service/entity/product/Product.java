package com.loctran.service.entity.product;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.comment.Comment;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.media.MediaType;
import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.productCompare.ProductCompare;
import com.loctran.service.entity.productNote.ProductNote;
import com.loctran.service.entity.year.Year;
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
    name = "tbl_product"
)
@JsonIdentityInfo(scope = Product.class, generator = PropertyGenerator.class, property = "id")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column
  private String name;

  @Column
  private String slug;

  @Column
  private String description;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "thumbnail_id", referencedColumnName = "id")
  private Media thumbnail;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "product_votes", joinColumns = @JoinColumn(name = "product_id"))
  @Column(name = "vote_user_id")
  private List<Long> votes = new ArrayList<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
  @JsonIgnore
  private List<Media> mediaList;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
  private List<Comment> comments;

  @Transient
  private List<Media> galleries;

  @Transient
  private List<Media> outfits;

  @ManyToMany(mappedBy = "products")
  private List<ProductNote> notes;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "country_id")
  private Country country;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "brand_id")
  private Brand brand;

  @OneToMany(mappedBy = "productOriginal", fetch = FetchType.LAZY)
  private List<ProductCompare> originalComparisons;

  @OneToMany(mappedBy = "productCompare", fetch = FetchType.LAZY)
  private List<ProductCompare> comparedInComparisons;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "dateReleased_id")
  private Year dateReleased;

  @CreationTimestamp
  @Column(name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

  @PostLoad
  public void postLoad(){
    this.galleries = mediaList.stream().filter(media -> media.getType().equals(MediaType.PRODUCT_GALLERY)).toList();
    this.outfits = mediaList.stream().filter(media -> media.getType().equals(MediaType.PRODUCT_OUTFIT)).toList();

  }


}


