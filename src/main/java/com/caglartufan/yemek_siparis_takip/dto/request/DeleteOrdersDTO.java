package com.caglartufan.yemek_siparis_takip.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteOrdersDTO {
    @NotEmpty(message = "Silinmek istenen siparişlerin kimlik bilgileri boş olamaz.")
    private List<Integer> orderIds;
}
