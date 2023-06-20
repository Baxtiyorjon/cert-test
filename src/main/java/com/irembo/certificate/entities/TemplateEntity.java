package com.irembo.certificate.entities;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.Set;

@Entity
@Table(name = "templates")
@Getter
@Setter
public class TemplateEntity {
  @Id
  private String templateIdentifier;
  private String name;
  @Column(columnDefinition = "text")
  private String source;

  @JdbcTypeCode(SqlTypes.JSON)
  private Set<String> placeholders;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

}
