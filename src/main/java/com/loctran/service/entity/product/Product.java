package com.loctran.service.entity.product;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.comment.Comment;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.productCompare.ProductCompare;
import com.loctran.service.entity.productNote.ProductNote;
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

  @Column
  private String thumbnail;

  @ManyToMany(mappedBy = "productVotedList")
  private List<User> votes;


  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product",cascade = CascadeType.MERGE)
  private List<Comment> comments;

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

}


