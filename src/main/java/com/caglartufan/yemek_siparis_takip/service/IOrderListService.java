package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.OrderListCreateDTO;
import com.caglartufan.yemek_siparis_takip.entity.OrderList;

import java.util.List;

public interface IOrderListService {
    OrderList findOrderListOrElseThrow(Integer id);

    List<OrderListDTO> list();

    List<OrderListDTO> listByVendor(Integer vendorId);

    OrderListDTO findById(Integer id);

    OrderListDTO create(OrderListCreateDTO dto);

    OrderListDTO delete(Integer id);
}