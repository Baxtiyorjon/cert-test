package com.irembo.certificate.services;

import com.irembo.certificate.config.properties.AppProperties;
import com.irembo.certificate.models.JwtResponse;
import com.irembo.certificate.security.model.LoginRequest;
import com.irembo.certificate.security.service.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final AppProperties appProperties;
  public JwtResponse generateToken(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    return new JwtResponse(jwt, appProperties.getJwtExpirationTime());
  }
}
