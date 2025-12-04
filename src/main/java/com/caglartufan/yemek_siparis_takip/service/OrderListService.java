package com.caglartufan.yemek_siparis_takip.service;

import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.OrderListCreateDTO;
import com.caglartufan.yemek_siparis_takip.entity.OrderList;
import com.caglartufan.yemek_siparis_takip.entity.Vendor;
import com.caglartufan.yemek_siparis_takip.mapper.OrderListMapper;
import com.caglartufan.yemek_siparis_takip.repository.OrderListRepository;
import com.caglartufan.yemek_siparis_takip.repository.VendorRepository;
import com.caglartufan.yemek_siparis_takip.util.OrderListUtil;
import com.caglartufan.yemek_siparis_takip.util.VendorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderListService implements IOrderListService {
    private final OrderListRepository orderListRepository;
    private final VendorRepository vendorRepository;
    private final OrderListMapper orderListMapper;

    public List<OrderListDTO> list() {
        return orderListMapper.toOrderListDTO(orderListRepository.findAll());
    }

    public List<OrderListDTO> listByVendor(Integer vendorId) {
        return orderListMapper.toOrderListDTO(orderListRepository.findByVendorId(vendorId));
    }

    @Transactional
    public OrderListDTO create(OrderListCreateDTO dto) {
        // Find the vendor or fail
        Vendor vendor = VendorUtil.findVendorOrElseThrow(dto.getVendorId(), vendorRepository);

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

    @Transactional
    public OrderListDTO delete(Integer id) {
        OrderList orderList = OrderListUtil.findOrderListOrElseThrow(id, orderListRepository);

        // Delete the order list
        orderListRepository.deleteById(id);

        // Return DTO of the deleted order list
        return orderListMapper.toOrderListDTO(orderList);
    }
}
