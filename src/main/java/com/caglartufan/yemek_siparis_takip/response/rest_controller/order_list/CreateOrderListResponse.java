package com.caglartufan.yemek_siparis_takip.response.rest_controller.order_list;

import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.response.MessageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateOrderListResponse extends MessageResponse {
    static private final boolean SUCCESS = true;
    static private final String MESSAGE = "Sipariş listesi başarıyla oluşturuldu!";

    private OrderListDTO orderList;

    public CreateOrderListResponse() {
        super(CreateOrderListResponse.SUCCESS, CreateOrderListResponse.MESSAGE);
    }

    public CreateOrderListResponse(OrderListDTO orderList) {
        super(CreateOrderListResponse.SUCCESS, CreateOrderListResponse.MESSAGE);

        this.orderList = orderList;
    }
}
