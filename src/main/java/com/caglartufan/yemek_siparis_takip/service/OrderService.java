package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.OrderCreateDTO;
import com.caglartufan.yemek_siparis_takip.entity.Order;
import com.caglartufan.yemek_siparis_takip.entity.OrderList;
import com.caglartufan.yemek_siparis_takip.exception.OrderNotFoundException;
import com.caglartufan.yemek_siparis_takip.mapper.OrderMapper;
import com.caglartufan.yemek_siparis_takip.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderListService orderListService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDTO> list(Integer orderListId) {
        orderListService.findOrderListOrElseThrow(orderListId);

        return orderMapper.toOrderDTOList(orderRepository.findByOrderListId(orderListId));
    }

    @Override
    public OrderDTO findById(Integer orderListId, Integer id) {
        OrderList orderList = orderListService.findOrderListOrElseThrow(orderListId);

        Order order = orderList
                .getOrders()
                .stream()
                .filter(orderEl -> orderEl.getId() == id)
                .findFirst()
                .orElseThrow(() -> new OrderNotFoundException(id));

        return orderMapper.toOrderDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO create(Integer orderListId, OrderCreateDTO dto) {
        OrderList orderList = orderListService.findOrderListOrElseThrow(orderListId);

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

    @Override
    @Transactional
    public List<OrderDTO> deleteWithIds(Integer orderListId, List<Integer> ids) {
        OrderList orderList = orderListService.findOrderListOrElseThrow(orderListId);

        List<Order> removedOrders = orderList.removeOrdersWithIds(ids);

        return orderMapper.toOrderDTOList(removedOrders);
    }
}
