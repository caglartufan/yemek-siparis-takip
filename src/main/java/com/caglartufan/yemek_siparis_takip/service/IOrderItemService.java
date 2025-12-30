package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.dto.OrderItemDTO;

import java.util.List;

public interface IOrderItemService {
    List<OrderItemDTO> list(Integer orderListId, Integer orderId);
}
