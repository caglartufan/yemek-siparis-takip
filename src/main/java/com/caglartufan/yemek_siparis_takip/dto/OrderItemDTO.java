package com.caglartufan.yemek_siparis_takip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private int id;
    private int quantity = 1;
    private BigDecimal portion = BigDecimal.ONE;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private OrderDTO order;
    private ProductDTO product;
}
