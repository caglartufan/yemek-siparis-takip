package com.caglartufan.yemek_siparis_takip.response.rest_controller.order;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import com.caglartufan.yemek_siparis_takip.response.MessageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PatchOrderResponse extends MessageResponse {
    static private final boolean SUCCESS = true;
    static private final String MESSAGE = "Sipariş başarıyla güncellendi!";

    private OrderDTO order;

    public PatchOrderResponse() {
        super(PatchOrderResponse.SUCCESS, PatchOrderResponse.MESSAGE);
    }

    public PatchOrderResponse(OrderDTO order) {
        super(PatchOrderResponse.SUCCESS, PatchOrderResponse.MESSAGE);

        this.order = order;
    }
}
