package com.caglartufan.yemek_siparis_takip.mapper;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import com.caglartufan.yemek_siparis_takip.dto.OrderItemDTO;
import com.caglartufan.yemek_siparis_takip.entity.Order;
import com.caglartufan.yemek_siparis_takip.entity.OrderItem;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Named("toOrderDTO")
    @Mapping(target = "orderList", ignore = true)
    @Mapping(target = "orderItems", qualifiedByName = "orderOrderListMapping")
    OrderDTO toOrderDTO(Order order);

    @IterableMapping(qualifiedByName = "toOrderDTO")
    List<OrderDTO> toOrderDTOList(List<Order> order);

    @Named("toOrderDTOWithoutOrderList")
    @Mapping(target = "orderList", ignore = true)
    OrderDTO toOrderDTOWithoutOrderList(Order order);

    Order fromOrderDTO(OrderDTO orderDTO);

    @Named("orderOrderListMapping")
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    OrderItemDTO orderOrderListMapping(OrderItem orderItem);
}
