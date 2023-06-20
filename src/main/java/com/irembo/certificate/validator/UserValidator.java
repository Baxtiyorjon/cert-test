package com.irembo.certificate.validator;

import com.irembo.certificate.exceptions.ValidationException;
import com.irembo.certificate.models.User;
import com.irembo.certificate.repositories.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {
  private final UserRepository userRepository;

  public void validateUserCreate(User user) {
    if (StringUtils.isEmpty(user.email())) {
      throw new ValidationException("Email can not be empty");
    }
    if (StringUtils.isEmpty(user.password())) {
      throw new ValidationException("Password can not be empty");
    }
    var userOptional = userRepository.findByEmail(user.email());
    if (userOptional.isPresent()) {
      throw new ValidationException(String.format("User with %s email is exists", user.email()));
    }
  }
}
