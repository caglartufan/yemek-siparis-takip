package com.caglartufan.yemek_siparis_takip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderListDTO {
    private Integer id;
    private String name;
    private BigDecimal totalPrice;
    private Instant createdAt;
    private VendorDTO vendor;
    private List<OrderDTO> orders = new ArrayList<>();
}
