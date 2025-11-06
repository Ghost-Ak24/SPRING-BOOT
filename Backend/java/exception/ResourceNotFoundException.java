package com.spring.backend.springboot_backend_1.exception;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue)
    {
        super(String.format("Resource %s not found", resourceName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
