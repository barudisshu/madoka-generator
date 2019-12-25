package org.cplier.codegen.annotation.constraint;

import org.cplier.codegen.annotation.UniquePortValidate;
import org.cplier.codegen.repository.MicroServiceRepository;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePortValidator implements ConstraintValidator<UniquePortValidate, Integer> {

  @Resource MicroServiceRepository repository;

  @Override
  public void initialize(UniquePortValidate constraintAnnotation) {}

  @Override
  public boolean isValid(Integer port, ConstraintValidatorContext constraintValidatorContext) {
    return !repository.existsMicroServiceByPort(port);
  }
}
