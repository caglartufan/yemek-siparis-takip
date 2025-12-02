package com.caglartufan.yemek_siparis_takip.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ResourceNotFoundResponse extends MessageResponse {
    public ResourceNotFoundResponse() {
        super(false, null);
    }

    public ResourceNotFoundResponse(String message) {
        super(false, message);
    }
}
