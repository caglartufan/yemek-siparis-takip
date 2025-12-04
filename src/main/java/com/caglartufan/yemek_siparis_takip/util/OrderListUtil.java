package com.caglartufan.yemek_siparis_takip.util;

import com.caglartufan.yemek_siparis_takip.entity.OrderList;
import com.caglartufan.yemek_siparis_takip.exception.OrderListNotFoundException;
import com.caglartufan.yemek_siparis_takip.repository.OrderListRepository;

public class OrderListUtil {
    public static OrderList findOrderListOrElseThrow(Integer id, OrderListRepository repository) {
        // Find the order list or fail
        return repository
                .findById(id)
                .orElseThrow(() -> new OrderListNotFoundException(id));
    }
}
