package com.irembo.certificate.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irembo.certificate.config.properties.AppProperties;
import com.irembo.certificate.constant.Constants;
import com.irembo.certificate.entities.CertificateEntity;
import com.irembo.certificate.entities.TemplateEntity;
import com.irembo.certificate.exceptions.CertificateCustomException;
import com.irembo.certificate.mapper.CertificateMapper;
import com.irembo.certificate.models.Certificate;
import com.irembo.certificate.models.CertificateData;
import com.irembo.certificate.models.CertificateStatus;
import com.irembo.certificate.models.Fields;
import com.irembo.certificate.repositories.CertificateRepository;
import com.irembo.certificate.repositories.TemplateRepository;
import com.irembo.certificate.security.service.JwtUtils;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificateService {
  private final CertificateRepository certificateRepository;
  private final AmazonS3 amazonS3;
  private final AppProperties appProperties;
  private final JwtUtils jwtUtils;
  private final CertificateMapper certificateMapper;
  private final RabbitTemplate rabbitTemplate;
  private final TemplateRepository templateRepository;
  private final ObjectMapper objectMapper;
  private final TemplateEngine templateEngine;

  public Certificate generateCertificate(TemplateEntity template, Fields fields)
      throws IOException {
    var filename = generateCertificateName();
    var entity = getCertificate(template, filename, fields);
    var savedEntity = certificateRepository.save(entity);
    var certificateData = new CertificateData(template.getTemplateIdentifier(), fields,
        savedEntity.getCertificateIdentifier());
    rabbitTemplate.convertAndSend(appProperties.getQueueName(),
        objectMapper.writeValueAsString(certificateData));
    return certificateMapper.toDTO(savedEntity);
  }


  public void generatePDFAndSendAWS(CertificateData certificateData) throws IOException {
    var template = templateRepository.findById(certificateData.getTemplateIdentifier()).get();
    var certificate =
        certificateRepository.findById(certificateData.getCertificateIdentifier()).get();
    var pdfFile = generetePDFFile(template, certificateData.getFields());
    uploadToAWS(pdfFile, certificate.getName());
    updateStatus(certificate, CertificateStatus.COMPLETED);
    pdfFile.delete();
  }

  public Certificate getCertificate(String certificateIdentifier) {
    var entity = certificateRepository.findById(certificateIdentifier).get();
    var certificate = certificateMapper.toDTO(entity);
    if (entity.getStatus() == CertificateStatus.COMPLETED) {
      var url = amazonS3.generatePresignedUrl(appProperties.getBucketName(),
          getCertificateKey(certificate.getName()), new Date(System.currentTimeMillis() + 3600000));
      certificate.setUnsignedUrl(url.toString());
    }
    return certificate;
  }

  private void updateStatus(CertificateEntity entity, CertificateStatus status) {
    entity.setStatus(status);
    certificateRepository.save(entity);
  }

  private PutObjectResult uploadToAWS(File file, String fileName) {
    return amazonS3.putObject(appProperties.getBucketName(),
        appProperties.getCertificateFolder() + "/" + fileName, file);
  }

  private String generateCertificateName() {
    return UUID.randomUUID().toString() + System.currentTimeMillis() + ".pdf";
  }

  private File generetePDFFile(TemplateEntity template, Fields fields)
      throws IOException {
    File pdfFile = File.createTempFile(template.getName(), ".pdf");
    try (FileOutputStream pos = new FileOutputStream(pdfFile)) {
      var context = new Context();
      context.setVariables(fields.getValues());
      var parsedHtml = templateEngine.process(template.getSource(), context);
      ITextRenderer renderer = new ITextRenderer();
      renderer.setDocumentFromString(parsedHtml);
      renderer.layout();
      renderer.createPDF(pos);
      return pdfFile;
    } catch (DocumentException e) {
    throw new CertificateCustomException("Was not able to generate pdf file");
    }
  }

  private String getCertificateKey(String name) {
    return appProperties.getCertificateFolder() + "/" + name;
  }

  private CertificateEntity getCertificate(TemplateEntity template, String filename,
      Fields fields) {
    var entity = new CertificateEntity();
    entity.setCertificateIdentifier(UUID.randomUUID().toString());
    entity.setTemplate(template);
    entity.setStatus(CertificateStatus.IN_PROGRESS);
    entity.setUser(jwtUtils.getCurrentUser());
    entity.setName(filename);
    entity.setFields(fields);
    entity.getFields().getValues().put(Constants.CERTIFICATE_IDENTIFIER, entity.getCertificateIdentifier());
    return entity;
  }

}
