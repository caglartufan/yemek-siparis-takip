package com.caglartufan.yemek_siparis_takip.dto.request.order_list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderListPatchDTO {
    private String name;
}
