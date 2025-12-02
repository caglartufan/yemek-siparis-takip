package com.caglartufan.yemek_siparis_takip.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderListCreateDTO {
    @NotBlank(message = "Sipariş listesi adı sağlanmadı.")
    private String name;

    @NotNull(message = "Geçersiz satıcı kimlik bilgisi.")
    @Positive(message = "Geçersiz satıcı kimlik bilgisi.")
    private Integer vendorId;
}
