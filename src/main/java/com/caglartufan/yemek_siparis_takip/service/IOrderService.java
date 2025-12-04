package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.OrderCreateDTO;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> list(Integer orderListId);

    OrderDTO create(Integer orderListId, OrderCreateDTO dto);
}
