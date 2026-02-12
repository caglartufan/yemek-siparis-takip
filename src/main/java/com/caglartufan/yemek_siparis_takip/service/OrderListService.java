package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import com.caglartufan.yemek_siparis_takip.dto.OrderItemDTO;
import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.order.DeleteOrdersDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.order.OrderCreateDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.order.OrderPatchDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.order_item.OrderItemCreateDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.order_list.OrderListCreateDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.order_list.OrderListPatchDTO;
import com.caglartufan.yemek_siparis_takip.entity.*;
import com.caglartufan.yemek_siparis_takip.exception.OrderItemNotFoundException;
import com.caglartufan.yemek_siparis_takip.exception.OrderListNotFoundException;
import com.caglartufan.yemek_siparis_takip.exception.OrderNotFoundException;
import com.caglartufan.yemek_siparis_takip.mapper.OrderItemMapper;
import com.caglartufan.yemek_siparis_takip.mapper.OrderListMapper;
import com.caglartufan.yemek_siparis_takip.mapper.OrderMapper;
import com.caglartufan.yemek_siparis_takip.repository.OrderItemRepository;
import com.caglartufan.yemek_siparis_takip.repository.OrderListRepository;
import com.caglartufan.yemek_siparis_takip.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderListService implements IOrderListService {
    private final IVendorService vendorService;
    private final IProductService productService;
    private final OrderListRepository orderListRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderListMapper orderListMapper;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    /**
     * ORDER LIST RELATED METHODS
     */
    @Override
    public OrderList findOrderListOrElseThrow(Integer orderListId) {
        // Find the order list or fail
        return orderListRepository
                .findById(orderListId)
                .orElseThrow(() -> new OrderListNotFoundException(orderListId));
    }

    @Override
    public List<OrderListDTO> list() {
        return orderListMapper.toOrderListListDTO(orderListRepository.findAll());
    }

    @Override
    public List<OrderListDTO> listByVendor(Integer vendorId) {
        return orderListMapper.toOrderListListDTO(orderListRepository.findByVendorId(vendorId));
    }

    @Override
    public OrderListDTO findById(Integer orderListId) {
        OrderList orderList = findOrderListOrElseThrow(orderListId);
        return orderListMapper.toOrderListDTOWithOrders(orderList);
    }

    @Override
    @Transactional
    public OrderListDTO create(OrderListCreateDTO dto) {
        // Find the vendor or fail
        Vendor vendor = vendorService.findVendorOrElseThrow(dto.getVendorId());

        // Create an order list
        OrderList orderList = new OrderList();
        orderList.setName(dto.getName());

        // Link vendor and order list
        vendor.addOrderList(orderList);

        // Save the order list
        OrderList savedOrderList = orderListRepository.save(orderList);

        // Return DTO of the created order list
        return orderListMapper.toOrderListDTO(savedOrderList);
    }

    @Override
    @Transactional
    public OrderListDTO patch(Integer orderListId, OrderListPatchDTO orderListPatchDTO) {
        OrderList orderList = findOrderListOrElseThrow(orderListId);
        String newName = orderListPatchDTO.getName();
        boolean hasChanged = false;

        if (!Objects.isNull(newName) && !orderList.getName().equals(newName)) {
            orderList.setName(newName);
            hasChanged = true;
        }

        if (hasChanged) {
            OrderList patchedOrderList = orderListRepository.save(orderList);
            return orderListMapper.toOrderListDTO(patchedOrderList);
        }

        return orderListMapper.toOrderListDTO(orderList);
    }

    @Override
    @Transactional
    public OrderListDTO delete(Integer orderListId) {
        OrderList orderList = findOrderListOrElseThrow(orderListId);

        // Delete the order list
        orderListRepository.deleteById(orderListId);

        // Return DTO of the deleted order list
        return orderListMapper.toOrderListDTO(orderList);
    }

    /**
     * ORDER RELATED METHODS
     */
    @Override
    public Order findOrderOrElseThrow(Integer orderListId, Integer orderId) {
        return orderRepository
                .findOrderOfOrderList(orderListId, orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Override
    public List<OrderDTO> listOrders(Integer orderListId) {
        OrderList orderList = findOrderListOrElseThrow(orderListId);

        return orderMapper.toOrderDTOList(orderList.getOrders());
    }

    @Override
    public OrderDTO findOrderById(Integer orderListId, Integer orderId) {
        findOrderListOrElseThrow(orderListId);

        Order order = findOrderOrElseThrow(orderListId, orderId);

        return orderMapper.toOrderDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO createOrder(Integer orderListId, OrderCreateDTO orderCreateDTO) {
        OrderList orderList = findOrderListOrElseThrow(orderListId);

        // Create order
        Order order = new Order();
        order.setOrderedBy(orderCreateDTO.getOrderedBy());

        // Add order to orderList
        orderList.addOrder(order);

        // Save order and retrieve saved order
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toOrderDTO(savedOrder);
    }

    @Override
    @Transactional
    public OrderDTO patchOrder(Integer orderListId, Integer orderId, OrderPatchDTO orderPatchDTO) {
        findOrderListOrElseThrow(orderListId);

        Order order = findOrderOrElseThrow(orderListId, orderId);
        String newOrderedBy = orderPatchDTO.getOrderedBy();
        boolean hasChanged = false;

        if (!Objects.isNull(newOrderedBy) && !order.getOrderedBy().equals(newOrderedBy)) {
            order.setOrderedBy(newOrderedBy);
            hasChanged = true;
        }

        if (hasChanged) {
            // Save order and retrieve patched order
            Order patchedOrder = orderRepository.save(order);
            return orderMapper.toOrderDTO(patchedOrder);
        }

        return orderMapper.toOrderDTO(order);
    }

    @Override
    @Transactional
    public List<OrderDTO> deleteOrdersWithIds(Integer orderListId, DeleteOrdersDTO deleteOrdersDTO) {
        OrderList orderList = findOrderListOrElseThrow(orderListId);

        // Remove orders with given ids
        List<Order> deletedOrders = orderList.removeOrdersWithIds(deleteOrdersDTO.getOrderIds());

        return orderMapper.toOrderDTOList(deletedOrders);
    }

    /**
     * ORDER ITEM RELATED METHODS
     */
    @Override
    public OrderItem findOrderItemOrElseThrow(Integer orderListId, Integer orderId, Integer orderItemId) {
        return orderItemRepository
                .findOrderItemOfOrderOfOrderList(orderListId, orderId, orderItemId)
                .orElseThrow(() -> new OrderItemNotFoundException(orderItemId));
    }

    @Override
    public List<OrderItemDTO> listOrderItems(Integer orderListId, Integer orderId) {
        List<OrderItem> orderItems = orderItemRepository
                .findOrderItemsOfOrderOfOrderList(orderListId, orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        return orderItemMapper.toOrderItemListDTO(orderItems);
    }

    @Override
    public OrderItemDTO findOrderItemById(Integer orderListId, Integer orderId, Integer orderItemId) {
        OrderItem orderItem = findOrderItemOrElseThrow(orderListId, orderId, orderItemId);

        return orderItemMapper.toOrderItemDTO(orderItem);
    }

    @Override
    @Transactional
    public OrderItemDTO createOrderItem(Integer orderListId, Integer orderId, OrderItemCreateDTO orderItemCreateDTO) {
        Order order = findOrderOrElseThrow(orderListId, orderId);
        OrderList orderList = order.getOrderList();
        Product product = productService
                .findProductOrElseThrow(orderList.getVendor().getId(), orderItemCreateDTO.getProductId());

        // Create orderItem
        OrderItem orderItem = OrderItem.createOrderItem(
                order,
                orderItemCreateDTO.getQuantity(),
                orderItemCreateDTO.getPortion(),
                product
        );

        // Add created orderItem through orderList
        orderList.addOrderItem(orderId, orderItem);

        // Save orderItem
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        return orderItemMapper.toOrderItemDTO(savedOrderItem);
    }
}
