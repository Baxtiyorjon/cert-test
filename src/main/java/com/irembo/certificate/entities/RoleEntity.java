package com.irembo.certificate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleEntity {
  @Id
  private Long id;

  @Column(name = "role_name", unique = true, nullable = false)
  private String name;
}
