package com.irembo.certificate.security.model;

import com.irembo.certificate.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {
  private Long id;
  private String email;
  private String password;
  private String phone;
  private Collection<? extends GrantedAuthority> authorities;

  public CustomUserDetails(UserEntity user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.phone = user.getPhone();
    this.authorities =
        user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toSet());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
