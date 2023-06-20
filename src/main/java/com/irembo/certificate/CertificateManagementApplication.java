package com.irembo.certificate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CertificateManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CertificateManagementApplication.class, args);
	}

}
