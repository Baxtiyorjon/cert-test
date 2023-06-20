package com.irembo.certificate.services;

import com.irembo.certificate.mapper.UserMapper;
import com.irembo.certificate.models.User;
import com.irembo.certificate.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public User create(User user) {
    var entity = userMapper.toEntity(user);
    entity.setPassword(passwordEncoder.encode(user.password()));
    var savedEntity = userRepository.save(entity);
    return userMapper.toDTO(savedEntity);
  }
}
