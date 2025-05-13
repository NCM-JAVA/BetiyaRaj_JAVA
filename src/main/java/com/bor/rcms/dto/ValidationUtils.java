package com.bor.rcms.dto;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

public class ValidationUtils {
    public static Map<String, String> validate(Object obj) {
        return Validation.buildDefaultValidatorFactory().getValidator()
            .validate(obj).stream()
            .collect(Collectors.toMap(
                v -> v.getPropertyPath().toString(),
                ConstraintViolation::getMessage
            ));
    }
}