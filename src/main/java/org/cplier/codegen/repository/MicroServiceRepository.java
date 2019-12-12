package org.cplier.codegen.repository;

import org.cplier.codegen.entity.MicroService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MicroServiceRepository extends CrudRepository<MicroService, Long> {}
