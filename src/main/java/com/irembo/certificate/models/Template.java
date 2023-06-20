package com.irembo.certificate.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Template {
  private String templateIdentifier;
  private String name;
  private List<String> placeholders;

}
