package com.loctran.service.user;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@RequiredArgsConstructor
public enum Role {
  USER,
  ADMIN;

  public List<SimpleGrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();

    grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return grantedAuthorityList;
  }
}