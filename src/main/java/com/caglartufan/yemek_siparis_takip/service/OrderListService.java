package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.OrderListCreateDTO;
import com.caglartufan.yemek_siparis_takip.entity.OrderList;
import com.caglartufan.yemek_siparis_takip.entity.Vendor;
import com.caglartufan.yemek_siparis_takip.exception.OrderListNotFoundException;
import com.caglartufan.yemek_siparis_takip.exception.VendorNotFoundException;
import com.caglartufan.yemek_siparis_takip.mapper.OrderListMapper;
import com.caglartufan.yemek_siparis_takip.repository.OrderListRepository;
import com.caglartufan.yemek_siparis_takip.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderListService implements IOrderListService {
    private final OrderListRepository orderListRepository;
    private final VendorRepository vendorRepository;
    private final OrderListMapper orderListMapper;

    @Autowired
    public OrderListService(OrderListRepository orderListRepository, VendorRepository vendorRepository, OrderListMapper orderListMapper) {
        this.orderListRepository = orderListRepository;
        this.vendorRepository = vendorRepository;
        this.orderListMapper = orderListMapper;
    }

    public List<OrderListDTO> list() {
        return orderListRepository.findAll().stream().map(orderListMapper::toOrderListDTO).toList();
    }

    public List<OrderListDTO> listByVendor(Integer vendorId) {
        return orderListRepository.findByVendorId(vendorId).stream().map(orderListMapper::toOrderListDTO).toList();
    }

    @Transactional
    public OrderListDTO create(OrderListCreateDTO dto) {
        // Find the vendor or fail
        Vendor vendor = vendorRepository
                .findById(dto.getVendorId())
                .orElseThrow(() -> new VendorNotFoundException(dto.getVendorId()));

        // Create an order list
        OrderList orderList = new OrderList();
        orderList.setName(dto.getName());
        vendor.addOrderList(orderList);

        // Save the order list
        OrderList savedOrderList = orderListRepository.save(orderList);

        // Return DTO of the created order list
        return orderListMapper.toOrderListDTO(savedOrderList);
    }

    @Transactional
    public OrderListDTO delete(Integer id) {
        // Find the order list or fail
        OrderList orderList = orderListRepository
                .findById(id)
                .orElseThrow(() -> new OrderListNotFoundException(id));

        // Delete the order list
        orderListRepository.deleteById(id);

        // Return DTO of the deleted order list
        return orderListMapper.toOrderListDTO(orderList);
    }
}
