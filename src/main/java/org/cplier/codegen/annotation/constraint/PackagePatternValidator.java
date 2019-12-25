package org.cplier.codegen.annotation.constraint;

import org.cplier.codegen.annotation.PackagePatternValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PackagePatternValidator
    implements ConstraintValidator<PackagePatternValidate, String> {

  @Override
  public void initialize(PackagePatternValidate constraintAnnotation) {}

  @Override
  public boolean isValid(
      String packageName, ConstraintValidatorContext constraintValidatorContext) {
    return packageName != null && packageName.matches("([a-zA_Z_][.\\w]*)");
  }
}
