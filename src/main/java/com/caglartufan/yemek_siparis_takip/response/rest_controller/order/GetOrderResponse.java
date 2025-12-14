package com.caglartufan.yemek_siparis_takip.response.rest_controller.order;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrderResponse {
    private OrderDTO order;
}
