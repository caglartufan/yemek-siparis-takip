package com.caglartufan.yemek_siparis_takip.dto;

import com.caglartufan.yemek_siparis_takip.entity.OrderList;
import com.caglartufan.yemek_siparis_takip.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorDTO {
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    private List<Product> products;
    private List<OrderList> orderLists;
}
