package com.golf.app.utils;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static Map<String, String> getFieldErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
}
