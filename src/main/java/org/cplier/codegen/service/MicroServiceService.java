package org.cplier.codegen.service;

import org.cplier.codegen.entity.MicroService;

import java.util.Optional;

public interface MicroServiceService {
  MicroService save(MicroService microService);

  Iterable<MicroService> findAll();

  Optional<MicroService> findById(long id);

  void delete(MicroService microService);
}
