package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.dto.OrderItemDTO;
import com.caglartufan.yemek_siparis_takip.entity.Order;
import com.caglartufan.yemek_siparis_takip.exception.OrderNotFoundException;
import com.caglartufan.yemek_siparis_takip.mapper.OrderItemMapper;
import com.caglartufan.yemek_siparis_takip.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService implements IOrderItemService {
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemDTO> list(Integer orderListId, Integer orderId) {
        Order order = orderRepository
                .findByIdAndOrderListId(orderId, orderListId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        return orderItemMapper.toOrderItemDTOWithOrderItems(order.getOrderItems());
    }
}
