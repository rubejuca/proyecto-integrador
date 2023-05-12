package com.rubejuca.proyectointegrador.services;

import java.util.Collection;

import org.springframework.util.Assert;

public class ValidationService {

    public static <T> T notNull(T value, String message) {
        Assert.notNull(value, message);
        return value;
    }

    public static <T> T isNotEmpty(T value, String message) {
        if (value instanceof String) {
            Assert.hasText((String) value, message);
        }
        if (value instanceof Collection) {
            Assert.notEmpty((Collection<?>) value, message);
        }
        return value;
    }

}
