package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> list(Integer orderListId);
}
