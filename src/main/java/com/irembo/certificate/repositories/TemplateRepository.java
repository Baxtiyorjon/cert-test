package com.irembo.certificate.repositories;

import com.irembo.certificate.entities.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity, String> {
}
