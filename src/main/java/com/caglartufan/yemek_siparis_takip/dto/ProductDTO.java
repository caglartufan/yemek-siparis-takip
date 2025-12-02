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
public class ProductDTO {
    private Integer id;
    private String name;
    private BigDecimal price;
    private VendorDTO vendor;
    private List<OrderItemDTO> orderItems = new ArrayList<>();
}
