package io.reflectoring.customvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidValuesConstraintValidator implements ConstraintValidator<ValidValues, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value.contains("xurupitas");
  }

}
