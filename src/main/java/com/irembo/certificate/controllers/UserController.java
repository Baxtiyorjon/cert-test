package com.irembo.certificate.controllers;

import com.irembo.certificate.models.JwtResponse;
import com.irembo.certificate.models.User;
import com.irembo.certificate.security.model.LoginRequest;
import com.irembo.certificate.services.TokenService;
import com.irembo.certificate.services.UserService;
import com.irembo.certificate.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/irembo/users")
public class UserController {
  private final UserService userService;
  private final UserValidator userValidator;
  private final TokenService tokenService;

  @PostMapping("/signin")
  public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(tokenService.generateToken(loginRequest));
  }

  @PostMapping("/create")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    userValidator.validateUserCreate(user);
    var newUser = userService.create(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }

}
