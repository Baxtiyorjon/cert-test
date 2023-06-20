package com.irembo.certificate.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@Component
public class AppProperties {
  private String bucketName;
  private String certificateFolder;
  private String awsSecretKey;
  private String awsSecretValue;
  private String jwtSecretKey;
  private Long jwtExpirationTime;
  private String queueName;
}
