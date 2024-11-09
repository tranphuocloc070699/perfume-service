package com.loctran.service.entity.product;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.comment.Comment;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.productCompare.ProductCompare;
import com.loctran.service.entity.productCompare.dto.ListProductCompareDto;
import com.loctran.service.entity.productNote.ProductNote;
import com.loctran.service.entity.productPrice.ProductPrice;
import com.loctran.service.entity.user.User;
import com.loctran.service.entity.year.Year;
import jakarta.persistence.*;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "tbl_product"
)
@JsonIdentityInfo(scope = Product.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
  @Lob
  private String description;

  @Column
  private String thumbnail;

  @ManyToMany(mappedBy = "productVotedList")
  private List<User> votes;


  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product",cascade = CascadeType.MERGE)
  private List<Comment> comments;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private List<ProductPrice> prices;


  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "product_galleries", joinColumns = @JoinColumn(name = "product_id"))
  @Column(name = "galleries")
  @OrderBy
  private Set<String> galleries;


  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "product_outfits", joinColumns = @JoinColumn(name = "product_id"))
  @Column(name = "outfits")
  @OrderBy
  private Set<String> outfits;

  @ManyToMany
  @JoinTable(name = "tbl_product_top_note",
          joinColumns = @JoinColumn(name = "product_id"),
          inverseJoinColumns = @JoinColumn(name = "note_id"))
  private List<ProductNote> topNotes;

  @ManyToMany
  @JoinTable(name = "tbl_product_middle_note",
          joinColumns = @JoinColumn(name = "product_id"),
          inverseJoinColumns = @JoinColumn(name = "note_id"))
  private List<ProductNote> middleNotes;

  @ManyToMany
  @JoinTable(name = "tbl_product_base_note",
          joinColumns = @JoinColumn(name = "product_id"),
          inverseJoinColumns = @JoinColumn(name = "note_id"))
  private List<ProductNote> baseNotes;


  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "country_id")
  private Country country;


  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "brand_id")
  private Brand brand;


  @JsonIgnore
  @OneToMany(mappedBy = "productOriginal", fetch = FetchType.LAZY)
  private List<ProductCompare> originalComparisons;

  @JsonIgnore
  @OneToMany(mappedBy = "productCompare", fetch = FetchType.LAZY)
  private List<ProductCompare> comparedInComparisons;

  @Transient
  private List<ListProductCompareDto> productCompares;


  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "dateReleased_id")
  private Year dateReleased;

  @CreationTimestamp
  @Column(name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

}


