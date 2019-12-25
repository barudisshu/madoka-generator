package org.cplier.codegen.annotation;

import org.cplier.codegen.annotation.constraint.UniqueIdentificationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueIdentificationValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueIdentificationValidate {

  String message() default "Identification already be used";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
