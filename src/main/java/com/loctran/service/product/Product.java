package com.loctran.service.product;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.loctran.service.media.Media;
import com.loctran.service.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "id")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id")
  private Long id;

  @Column
  private String name;

  @Column
  private String description;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "thumbnail_id", referencedColumnName = "id")
  private Media thumbnail;

  @OneToMany(mappedBy = "productGallery", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Media> gallery;

  @OneToMany(mappedBy = "productOutfit", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Media> outfit;

  private Integer dateReleased;

  @CreationTimestamp
  @Column(name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

}


