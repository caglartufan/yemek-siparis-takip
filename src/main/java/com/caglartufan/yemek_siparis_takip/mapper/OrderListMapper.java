package com.caglartufan.yemek_siparis_takip.mapper;

import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.entity.OrderList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {VendorMapper.class})
public interface OrderListMapper {
    OrderListDTO toOrderListDTO(OrderList orderList);

    OrderList fromOrderListDTO(OrderListDTO orderListDTO);
}
