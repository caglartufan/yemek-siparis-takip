package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.OrderListCreateDTO;
import com.caglartufan.yemek_siparis_takip.entity.OrderList;
import com.caglartufan.yemek_siparis_takip.entity.Vendor;
import com.caglartufan.yemek_siparis_takip.exception.OrderListNotFoundException;
import com.caglartufan.yemek_siparis_takip.mapper.OrderListMapper;
import com.caglartufan.yemek_siparis_takip.repository.OrderListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderListService implements IOrderListService {
    private final IVendorService vendorService;
    private final OrderListRepository orderListRepository;
    private final OrderListMapper orderListMapper;

    @Override
    public OrderList findOrderListOrElseThrow(Integer id) {
        // Find the order list or fail
        return orderListRepository
                .findById(id)
                .orElseThrow(() -> new OrderListNotFoundException(id));
    }

    @Override
    public List<OrderListDTO> list() {
        return orderListMapper.toOrderListDTO(orderListRepository.findAll());
    }

    @Override
    public List<OrderListDTO> listByVendor(Integer vendorId) {
        return orderListMapper.toOrderListDTO(orderListRepository.findByVendorId(vendorId));
    }

    @Override
    public OrderListDTO findById(Integer id) {
        OrderList orderList = findOrderListOrElseThrow(id);
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
    public OrderListDTO delete(Integer id) {
        OrderList orderList = findOrderListOrElseThrow(id);

        // Delete the order list
        orderListRepository.deleteById(id);

        // Return DTO of the deleted order list
        return orderListMapper.toOrderListDTO(orderList);
    }
}
