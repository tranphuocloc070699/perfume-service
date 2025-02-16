package com.loctran.service.entity.brand;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.product.Product;
import jakarta.persistence.*;
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
    name = "tbl_brand"
)
@JsonIdentityInfo(scope = Brand.class,generator = PropertyGenerator.class, property = "id")
public class Brand {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column
  private String name;

  @Column
  private String description;

  @Column
  private String homepageLink;

  @Column
  private String thumbnail;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "country_id")
  @ToString.Exclude
  private Country country;

  @JsonIgnore
  @OneToMany(fetch = FetchType.EAGER,mappedBy = "brand")
  @ToString.Exclude
  private List<Product> products;

  @CreationTimestamp
  @Column(name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

}
