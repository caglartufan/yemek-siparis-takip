package com.caglartufan.yemek_siparis_takip.response.rest_controller.order_list;

import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.response.MessageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PatchOrderListResponse extends MessageResponse {
    static private final boolean SUCCESS = true;
    static private final String MESSAGE = "Sipariş listesi başarıyla güncellendi!";

    private OrderListDTO orderList;

    public PatchOrderListResponse() {
        super(PatchOrderListResponse.SUCCESS, PatchOrderListResponse.MESSAGE);
    }

    public PatchOrderListResponse(OrderListDTO orderList) {
        super(PatchOrderListResponse.SUCCESS, PatchOrderListResponse.MESSAGE);

        this.orderList = orderList;
    }
}
