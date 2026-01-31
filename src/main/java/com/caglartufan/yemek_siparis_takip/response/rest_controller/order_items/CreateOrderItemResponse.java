package com.caglartufan.yemek_siparis_takip.response.rest_controller.order_items;

import com.caglartufan.yemek_siparis_takip.dto.OrderItemDTO;
import com.caglartufan.yemek_siparis_takip.response.MessageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateOrderItemResponse extends MessageResponse {
    static private final boolean SUCCESS = true;
    static private final String MESSAGE = "Sipariş öğesi başarıyla oluşturuldu!";

    private OrderItemDTO orderItem;

    public CreateOrderItemResponse() {
        super(CreateOrderItemResponse.SUCCESS, CreateOrderItemResponse.MESSAGE);
    }

    public CreateOrderItemResponse(OrderItemDTO orderItem) {
        super(CreateOrderItemResponse.SUCCESS, CreateOrderItemResponse.MESSAGE);

        this.orderItem = orderItem;
    }
}
