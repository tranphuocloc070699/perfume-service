package com.loctran.service.entity.product;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.color.Color;
import com.loctran.service.entity.comment.Comment;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.productCompare.ProductCompare;
import com.loctran.service.entity.productCompare.dto.ListProductCompareDto;
import com.loctran.service.entity.productNote.ProductNote;
import com.loctran.service.entity.productPrice.ProductPrice;
import com.loctran.service.entity.year.Year;
import jakarta.persistence.*;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(columnDefinition = "TEXT")
  private String fengShui;

  @Column
  private String thumbnail;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "product_votes", joinColumns = @JoinColumn(name = "product_id"))
  @Column(name = "votes")
  @OrderBy
  private Set<Long> votes;


  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product",cascade = CascadeType.MERGE)
  private List<Comment> comments;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private List<ProductPrice> prices;


  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "product_galleries", joinColumns = @JoinColumn(name = "product_id"))
  @Column(name = "galleries")
  @OrderBy
  private Set<String> galleries;


//  @ElementCollection(fetch = FetchType.LAZY)
//  @CollectionTable(name = "product_outfits", joinColumns = @JoinColumn(name = "product_id"))
//  @Column(name = "outfits")
//  @OrderBy
//  private Set<String> outfits;


  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "product_top_notes",
          joinColumns = @JoinColumn(name = "product_id"),
          inverseJoinColumns = @JoinColumn(name = "note_id"))
  private Set<ProductNote> topNotes;

    @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "product_middle_notes",
          joinColumns = @JoinColumn(name = "product_id"),
          inverseJoinColumns = @JoinColumn(name = "note_id"))
  private Set<ProductNote> middleNotes;

    @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "product_base_notes",
          joinColumns = @JoinColumn(name = "product_id"),
          inverseJoinColumns = @JoinColumn(name = "note_id"))
  private Set<ProductNote> baseNotes;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "product_colors",
      joinColumns = @JoinColumn(name = "product_id"),
      inverseJoinColumns = @JoinColumn(name = "color_id"))
  private Set<Color> colors;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "country_id")
  @ToString.Exclude
  private Country country;


  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "brand_id")
  @ToString.Exclude
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
  @ToString.Exclude
  private Year dateReleased;

  @CreationTimestamp
  @Column(name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

}


