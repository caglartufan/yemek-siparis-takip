package com.caglartufan.yemek_siparis_takip.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class MessageResponse extends BaseResponse {
    String message;

    public MessageResponse(boolean success, String message) {
        super(success);

        this.message = message;
    }
}
