package com.irembo.certificate.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateRequest {
  private String source;
  private String name;
  private Set<String> placeholders;
}
