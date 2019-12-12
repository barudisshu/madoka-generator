package org.cplier.codegen.service;

import org.cplier.codegen.entity.User;

import java.util.Optional;

public interface UserService {
  User save(User user);

  Iterable<User> findAll();

  Optional<User> findById(long id);

  void delete(User user);
}
