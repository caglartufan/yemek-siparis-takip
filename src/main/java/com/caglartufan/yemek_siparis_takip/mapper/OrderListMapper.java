package com.caglartufan.yemek_siparis_takip.mapper;

import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.entity.OrderList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderListMapper {
    @Mapping(target = "vendor.products", ignore = true)
    @Mapping(target = "vendor.orderLists", ignore = true)
    @Mapping(target = "orders", ignore = true)
    OrderListDTO toOrderListDTO(OrderList orderList);

    List<OrderListDTO> toOrderListDTO(List<OrderList> orderList);

    OrderList fromOrderListDTO(OrderListDTO orderListDTO);
}
