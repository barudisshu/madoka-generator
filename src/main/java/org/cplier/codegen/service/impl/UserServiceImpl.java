package org.cplier.codegen.service.impl;

import org.cplier.codegen.entity.User;
import org.cplier.codegen.repository.UserRepository;
import org.cplier.codegen.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  @Resource UserRepository userRepository;

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public Iterable<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> findById(long id) {
    return userRepository.findById(id);
  }

  @Override
  public void delete(User user) {
    userRepository.delete(user);
  }
}
