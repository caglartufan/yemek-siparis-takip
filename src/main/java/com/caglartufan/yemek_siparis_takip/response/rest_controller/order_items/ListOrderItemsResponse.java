package com.caglartufan.yemek_siparis_takip.response.rest_controller.order_items;


import com.caglartufan.yemek_siparis_takip.dto.OrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListOrderItemsResponse {
    private List<OrderItemDTO> orderItems;
}
