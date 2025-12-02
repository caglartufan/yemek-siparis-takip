package com.caglartufan.yemek_siparis_takip.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ValidationFailedResponse extends BaseResponse {
    private Map<String, String> errors;

    public ValidationFailedResponse() {
        super(false);
    }

    public ValidationFailedResponse(Map<String, String> errors) {
        super(false);

        this.errors = errors;
    }
}
