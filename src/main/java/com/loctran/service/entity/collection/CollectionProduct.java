package com.loctran.service.entity.collection;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.loctran.service.entity.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "tbl_collection_product"
)
@JsonIdentityInfo(scope = CollectionProduct.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class  CollectionProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "collection_id", nullable = false)
    @ToString.Exclude
    private Collection collection;

    @Column(nullable = false)
    private Integer index;
}
