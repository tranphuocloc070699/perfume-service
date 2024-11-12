package com.loctran.service.entity.productNote;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.productNote.dto.ProductNoteSerializer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "tbl_product_note"
)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonSerialize(using = ProductNoteSerializer.class)
public class ProductNote {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column
  private String name;

  @Column
  private String enName;

  @Column
  private String slug;

  @Column
  private String thumbnail;


//  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//  @EqualsAndHashCode.Exclude
//  @ToString.Exclude
//  @JoinTable(name = "tbl_product_tbl_product_note",
//      joinColumns = @JoinColumn(name = "tbl_product_note_id"),
//      inverseJoinColumns = @JoinColumn(name = "tbl_product_id")
//  )
//  private Set<Product> products;

  @CreationTimestamp
  @Column(name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

}
