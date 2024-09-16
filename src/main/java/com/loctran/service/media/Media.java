package com.loctran.service.media;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.loctran.service.product.Product;
import com.loctran.service.user.Role;
import com.loctran.service.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "tbl_media"
)
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "id")
public class Media {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id")
  private Long id;

  @Column
  private String path;

  @Enumerated(EnumType.STRING)
  private MediaType type;

  @ManyToOne
  @JoinColumn(name = "product_gallery_id")
  private Product productGallery;

  @ManyToOne
  @JoinColumn(name = "product_outfit_id")
  private Product productOutfit;

}