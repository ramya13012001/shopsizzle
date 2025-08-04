package com.Shop.Sizzle.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private String fieldName;
    private Long fieldId;
    private String resouceName;
    private String field;

    public ResourceNotFoundException(String resouceName, String field, Long fieldId) {
        super(String.format("%s not found with %s:%d", resouceName, field, fieldId));
        this.fieldId = fieldId;
        this.resouceName = resouceName;
        this.field = field;
    }

}
