package com.irembo.certificate.repositories;

import com.irembo.certificate.entities.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<CertificateEntity, String> {

}
