package org.cplier.codegen.service.impl;

import org.cplier.codegen.entity.MicroService;
import org.cplier.codegen.repository.MicroServiceRepository;
import org.cplier.codegen.service.MicroServiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class MicroServiceServiceImpl implements MicroServiceService {

  @Resource
  MicroServiceRepository microServiceRepository;

  @Override
  public MicroService save(MicroService microService) {
    return microServiceRepository.save(microService);
  }

  @Override
  public Iterable<MicroService> findAll() {
    return microServiceRepository.findAll();
  }

  @Override
  public Optional<MicroService> findById(long id) {
    return microServiceRepository.findById(id);
  }

  @Override
  public void delete(MicroService microService) {
    microServiceRepository.delete(microService);
  }
}
