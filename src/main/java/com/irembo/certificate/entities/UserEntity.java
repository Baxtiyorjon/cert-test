package com.irembo.certificate.entities;

import com.irembo.certificate.security.model.CustomUserDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "phone", nullable = false, unique = true)
  private String phone;

  @OneToMany
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private List<RoleEntity> roles;

  public UserEntity (CustomUserDetails customUserDetails) {
    this.id = customUserDetails.getId();
    this.phone = customUserDetails.getPhone();
    this.password = customUserDetails.getPassword();
    this.email = customUserDetails.getEmail();
  }

  public UserEntity() {
  }
}
