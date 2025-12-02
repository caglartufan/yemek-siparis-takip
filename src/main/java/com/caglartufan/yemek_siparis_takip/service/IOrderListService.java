package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.OrderListCreateDTO;

import java.util.List;

public interface IOrderListService {
    List<OrderListDTO> list();

    List<OrderListDTO> listByVendor(Integer vendorId);

    OrderListDTO create(OrderListCreateDTO dto);

    OrderListDTO delete(Integer id);
}