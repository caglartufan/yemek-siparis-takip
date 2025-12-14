package com.caglartufan.yemek_siparis_takip.response.rest_controller.order;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import com.caglartufan.yemek_siparis_takip.response.MessageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class DeleteOrdersResponse extends MessageResponse {
    static final boolean SUCCESS = true;
    static final String MESSAGE = "Siparişler başarıyla silindi!";

    private List<OrderDTO> orders;

    public DeleteOrdersResponse() {
        super(DeleteOrdersResponse.SUCCESS, DeleteOrdersResponse.MESSAGE);
    }

    public DeleteOrdersResponse(List<OrderDTO> orders) {
        super(DeleteOrdersResponse.SUCCESS, DeleteOrdersResponse.MESSAGE);
        this.orders = orders;
    }
}
