package com.caglartufan.yemek_siparis_takip.response.rest_controller.order_item;

import com.caglartufan.yemek_siparis_takip.dto.OrderItemDTO;
import com.caglartufan.yemek_siparis_takip.response.MessageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PatchOrderItemResponse extends MessageResponse {
    static private final boolean SUCCESS = true;
    static private final String MESSAGE = "Sipariş öğesi başarıyla güncellendi!";

    private OrderItemDTO orderItem;

    public PatchOrderItemResponse() {
        super(PatchOrderItemResponse.SUCCESS, PatchOrderItemResponse.MESSAGE);
    }

    public PatchOrderItemResponse(OrderItemDTO orderItem) {
        super(PatchOrderItemResponse.SUCCESS, PatchOrderItemResponse.MESSAGE);

        this.orderItem = orderItem;
    }
}
