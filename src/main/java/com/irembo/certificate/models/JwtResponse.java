package com.irembo.certificate.models;


public record JwtResponse(String token, Long expirationDate) {
}
