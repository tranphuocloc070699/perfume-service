package com.loctran.service.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.loctran.service.entity.comment.Comment;
import com.loctran.service.entity.post.Post;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.question.Question;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Collection;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(
    name = "tbl_user",
    uniqueConstraints = {
        @UniqueConstraint(name = "tbl_user_email_unique", columnNames = {"email"})
    }
)
public class User implements UserDetails {

  @jakarta.persistence.Id
  @Id
  @SequenceGenerator(
      name = "tbl_user_id_seq",
      sequenceName = "tbl_user_id_seq",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "tbl_user_id_seq"
  )
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "varchar(50) default 'ADMIN'")
  private Role role = Role.ADMIN;

  @Column
  private String avatar;


  @JsonIgnore
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @JoinTable(name = "tbl_product_tbl_user",
      joinColumns = @JoinColumn(name = "tbl_user_id"),
      inverseJoinColumns = @JoinColumn(name = "tbl_product_id")
  )
  private List<Product> productVotedList;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String name;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
  private List<Post> posts;

  @JsonIgnore
  @Column(nullable = false)
  private String password;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private List<Comment> comments;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private List<Question> questions;

  @JsonIgnore
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (role == null) {
      return List.of(new SimpleGrantedAuthority(Role.ADMIN.name()));
    }
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @JsonIgnore
  @Override
  public String getUsername() {
    return email;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isEnabled() {
    return true;
  }

}