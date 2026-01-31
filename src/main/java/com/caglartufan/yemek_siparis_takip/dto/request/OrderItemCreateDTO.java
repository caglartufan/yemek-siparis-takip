package com.caglartufan.yemek_siparis_takip.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemCreateDTO {
    @NotNull(message = "Adet boş olamaz.")
    @Min(value = 1, message = "Adet en az 1 olmalıdır.")
    @Max(value = 100, message = "Adet en fazla 100 olabilir.")
    private Integer quantity = 1;

    @NotNull(message = "Porsiyon boş olamaz.")
    @DecimalMin(value = "0.00", inclusive = false, message = "Porsiyon pozitif sayı olmalıdır.")
    @DecimalMax(value = "10.00", message = "Porsiyon en fazla 10 olabilir.")
    private BigDecimal portion = BigDecimal.ONE;

    @NotNull(message = "Geçersiz ürün kimlik bilgisi.")
    @Positive(message = "Geçersiz ürün kimlik bilgisi.")
    private Integer productId;
}
