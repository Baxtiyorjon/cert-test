package com.irembo.certificate.entities;

import com.irembo.certificate.models.CertificateStatus;
import com.irembo.certificate.models.Fields;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import io.hypersistence.utils.hibernate.type.json.JsonNodeStringType;
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


@Entity
@Table(name = "certificates")
@Getter
@Setter
public class CertificateEntity {
  @Id
  @Column(name = "certificate_identifier")
  private String certificateIdentifier;

  private String name;

  @JdbcTypeCode(SqlTypes.JSON)
  private Fields fields;

  private CertificateStatus status;

  @JoinColumn(name = "user_id")
  @ManyToOne
  private UserEntity user;

  @JoinColumn(name = "template_id")
  @ManyToOne
  private TemplateEntity template;
}
