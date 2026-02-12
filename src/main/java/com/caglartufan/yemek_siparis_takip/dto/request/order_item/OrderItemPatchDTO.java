package com.caglartufan.yemek_siparis_takip.dto.request.order_item;

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
public class OrderItemPatchDTO {
    @Min(value = 1, message = "Adet en az 1 olmalıdır.")
    @Max(value = 100, message = "Adet en fazla 100 olabilir.")
    private Integer quantity = 1;

    @DecimalMin(value = "0.00", inclusive = false, message = "Porsiyon pozitif sayı olmalıdır.")
    @DecimalMax(value = "10.00", message = "Porsiyon en fazla 10 olabilir.")
    private BigDecimal portion = BigDecimal.ONE;

    @Positive(message = "Geçersiz ürün kimlik bilgisi.")
    private Integer productId;
}
