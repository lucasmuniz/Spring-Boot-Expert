package io.github.lucasgm.validation;

import io.github.lucasgm.validation.constraintvalidator.ConstraintNotEmptyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ConstraintNotEmptyListValidator.class)
public @interface NotEmptyList {

    String message() default "A lista não pode ser vazia";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
