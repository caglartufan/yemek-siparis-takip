package com.caglartufan.yemek_siparis_takip.response.rest_controller.order;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import com.caglartufan.yemek_siparis_takip.response.MessageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateOrderResponse extends MessageResponse {
    static private final boolean SUCCESS = true;
    static private final String MESSAGE = "Sipariş başarıyla oluşturuldu!";

    private OrderDTO order;

    public CreateOrderResponse() {
        super(CreateOrderResponse.SUCCESS, CreateOrderResponse.MESSAGE);
    }

    public CreateOrderResponse(OrderDTO order) {
        super(CreateOrderResponse.SUCCESS, CreateOrderResponse.MESSAGE);

        this.order = order;
    }
}
