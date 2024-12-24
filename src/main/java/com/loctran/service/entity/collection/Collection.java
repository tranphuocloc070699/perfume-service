package com.loctran.service.entity.collection;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.loctran.service.entity.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "tbl_collection"
)
@JsonIdentityInfo(scope = Collection.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column
    private String icon;

    @Column
    private String title;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "collection",cascade = CascadeType.ALL)
    private Set<CollectionProduct> collectionProducts;

    @Column
    private Integer index;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}