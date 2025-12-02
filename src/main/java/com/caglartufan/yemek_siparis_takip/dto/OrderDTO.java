package com.caglartufan.yemek_siparis_takip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Integer id;
    private String orderedBy;
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private OrderListDTO orderList;
    private List<OrderItemDTO> orderItems = new ArrayList<>();
}
