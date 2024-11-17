package com.loctran.service.entity.answer;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.loctran.service.entity.comment.Comment;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.question.Question;
import com.loctran.service.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "tbl_answer"
)
@JsonIdentityInfo(scope = com.loctran.service.entity.answer.Answer.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "answer_thumbnails", joinColumns = @JoinColumn(name = "answer_id"))
    @Column(name = "thumbnails")
    @OrderBy
    private Set<String> thumbnails;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private User user;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "answer")
    private List<Comment> comments;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Question question;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "answer_votes", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "votes")
    @OrderBy
    private Set<Long> votes;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}