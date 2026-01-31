package com.caglartufan.yemek_siparis_takip.response.rest_controller.order_items;

import com.caglartufan.yemek_siparis_takip.dto.OrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrderItemResponse {
    private OrderItemDTO orderItem;
}
