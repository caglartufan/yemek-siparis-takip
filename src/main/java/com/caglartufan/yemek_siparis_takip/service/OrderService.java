package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.OrderCreateDTO;
import com.caglartufan.yemek_siparis_takip.entity.Order;
import com.caglartufan.yemek_siparis_takip.entity.OrderList;
import com.caglartufan.yemek_siparis_takip.mapper.OrderMapper;
import com.caglartufan.yemek_siparis_takip.repository.OrderListRepository;
import com.caglartufan.yemek_siparis_takip.repository.OrderRepository;
import com.caglartufan.yemek_siparis_takip.util.OrderListUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final OrderListRepository orderListRepository;
    private final OrderMapper orderMapper;

    public List<OrderDTO> list(Integer orderListId) {
        OrderListUtil.findOrderListOrElseThrow(orderListId, orderListRepository);

        return orderMapper.toOrderDTOList(orderRepository.findByOrderListId(orderListId));
    }

    @Transactional
    public OrderDTO create(Integer orderListId, OrderCreateDTO dto) {
        OrderList orderList = OrderListUtil.findOrderListOrElseThrow(orderListId, orderListRepository);

        // Create order
        Order order = new Order();
        order.setOrderedBy(dto.getOrderedBy());

        // Link order and order list
        orderList.addOrder(order);

        // Save the order
        Order savedOrder = orderRepository.save(order);

        // Return DTO of created order
        return orderMapper.toOrderDTO(savedOrder);
    }
}
