package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import com.caglartufan.yemek_siparis_takip.exception.OrderListNotFoundException;
import com.caglartufan.yemek_siparis_takip.mapper.OrderMapper;
import com.caglartufan.yemek_siparis_takip.repository.OrderListRepository;
import com.caglartufan.yemek_siparis_takip.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final OrderListRepository orderListRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderListRepository orderListRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderListRepository = orderListRepository;
        this.orderMapper = orderMapper;
    }

    public List<OrderDTO> list(Integer orderListId) {
        // Find the order list or fail
        orderListRepository
                .findById(orderListId)
                .orElseThrow(() -> new OrderListNotFoundException(orderListId));

        return orderRepository.findByOrderListId(orderListId).stream().map(orderMapper::toOrderDTO).toList();
    }
}
