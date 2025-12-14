package com.caglartufan.yemek_siparis_takip.response.rest_controller.order_list;

import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrderListResponse {
    private OrderListDTO orderList;
}
