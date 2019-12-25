package org.cplier.codegen.annotation;

import org.cplier.codegen.annotation.constraint.UniquePortValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniquePortValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePortValidate {

  String message() default "Port already be used";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
