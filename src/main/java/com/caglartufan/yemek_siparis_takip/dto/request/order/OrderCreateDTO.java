package com.caglartufan.yemek_siparis_takip.dto.request.order;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateDTO {
    @NotBlank(message = "Kişi adı sağlanmadı.")
    private String orderedBy;
}
