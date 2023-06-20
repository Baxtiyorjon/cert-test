package com.irembo.certificate.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.irembo.certificate.models.CertificateData;
import com.irembo.certificate.services.CertificateService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessagingListener {
  private final CertificateService certificateService;
  private final ObjectMapper objectMapper;

  public MessagingListener(CertificateService certificateService, ObjectMapper objectMapper) {
    this.certificateService = certificateService;
    this.objectMapper = objectMapper;
  }

  @RabbitListener(queues = "certificate")
  public void receiveMessage(String message) throws IOException {
    var certificateData = objectMapper.readValue(message, CertificateData.class);
    certificateService.generatePDFAndSendAWS(certificateData);
  }

}
