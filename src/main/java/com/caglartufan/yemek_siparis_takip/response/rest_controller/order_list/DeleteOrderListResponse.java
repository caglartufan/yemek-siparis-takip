package com.caglartufan.yemek_siparis_takip.response.rest_controller.order_list;

import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.response.MessageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class DeleteOrderListResponse extends MessageResponse {
    private static final boolean SUCCESS = true;
    private static final String MESSAGE = "Sipariş listesi başarıyla silindi!";

    private OrderListDTO orderList;

    public DeleteOrderListResponse() {
        super(DeleteOrderListResponse.SUCCESS, DeleteOrderListResponse.MESSAGE);
    }

    public DeleteOrderListResponse(OrderListDTO orderList) {
        super(DeleteOrderListResponse.SUCCESS, DeleteOrderListResponse.MESSAGE);

        this.orderList = orderList;
    }

}
