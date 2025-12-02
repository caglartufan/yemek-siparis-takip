package com.caglartufan.yemek_siparis_takip.mapper;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import com.caglartufan.yemek_siparis_takip.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderListMapper.class})
public interface OrderMapper {
    OrderDTO toOrderDTO(Order order);

    Order fromOrderDTO(OrderDTO orderDTO);
}
