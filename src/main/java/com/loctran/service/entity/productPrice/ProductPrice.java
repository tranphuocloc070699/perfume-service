package com.loctran.service.entity.productPrice;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.comment.Comment;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.productCompare.ProductCompare;
import com.loctran.service.entity.productNote.ProductNote;
import com.loctran.service.entity.user.User;
import com.loctran.service.entity.year.Year;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "tbl_product_price"
)
@JsonIdentityInfo(scope = com.loctran.service.entity.productPrice.ProductPrice.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @Column
    private LabelType labelType;

    @Column
    private PriceType priceType;

    @Column
    private Long value;

    @Column
    private Boolean isSearch;

    @Column
    private String link;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Product product;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}

