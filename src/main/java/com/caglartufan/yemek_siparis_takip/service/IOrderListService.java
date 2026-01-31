package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import com.caglartufan.yemek_siparis_takip.dto.OrderItemDTO;
import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.DeleteOrdersDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.OrderCreateDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.OrderItemCreateDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.OrderListCreateDTO;
import com.caglartufan.yemek_siparis_takip.entity.Order;
import com.caglartufan.yemek_siparis_takip.entity.OrderItem;
import com.caglartufan.yemek_siparis_takip.entity.OrderList;

import java.util.List;

public interface IOrderListService {
    /**
     * ORDER LIST RELATED METHODS
     */
    OrderList findOrderListOrElseThrow(Integer id);

    List<OrderListDTO> list();

    List<OrderListDTO> listByVendor(Integer vendorId);

    OrderListDTO findById(Integer id);

    OrderListDTO create(OrderListCreateDTO dto);

    OrderListDTO delete(Integer id);

    /**
     * ORDER RELATED METHODS
     */
    Order findOrderOrElseThrow(Integer orderListId, Integer orderId);

    List<OrderDTO> listOrders(Integer orderListId);

    OrderDTO findOrderById(Integer orderListId, Integer orderId);

    OrderDTO createOrder(Integer orderListId, OrderCreateDTO orderCreateDTO);

    List<OrderDTO> deleteOrdersWithIds(Integer orderListId, DeleteOrdersDTO deleteOrdersDTO);

    /**
     * ORDER ITEM RELATED METHODS
     */
    OrderItem findOrderItemOrElseThrow(Integer orderListId, Integer orderId, Integer orderItemId);

    List<OrderItemDTO> listOrderItems(Integer orderListId, Integer orderId);

    OrderItemDTO findOrderItemById(Integer orderListId, Integer orderId, Integer orderItemId);

    OrderItemDTO createOrderItem(Integer orderListId, Integer orderId, OrderItemCreateDTO orderItemCreateDTO);
}