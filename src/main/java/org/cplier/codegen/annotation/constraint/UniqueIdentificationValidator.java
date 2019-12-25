package org.cplier.codegen.annotation.constraint;

import org.cplier.codegen.annotation.UniqueIdentificationValidate;
import org.cplier.codegen.repository.MicroServiceRepository;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueIdentificationValidator
    implements ConstraintValidator<UniqueIdentificationValidate, String> {

  @Resource MicroServiceRepository repository;

  @Override
  public void initialize(UniqueIdentificationValidate constraintAnnotation) {}

  @Override
  public boolean isValid(
      String identification, ConstraintValidatorContext constraintValidatorContext) {
    return !repository.existsMicroServiceByIdentification(identification);
  }
}
