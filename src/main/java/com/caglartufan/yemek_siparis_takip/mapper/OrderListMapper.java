package com.caglartufan.yemek_siparis_takip.mapper;

import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.entity.OrderList;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface OrderListMapper {
    @Named("toOrderListDTO")
    @Mapping(target = "vendor.products", ignore = true)
    @Mapping(target = "vendor.orderLists", ignore = true)
    @Mapping(target = "orders", ignore = true)
    OrderListDTO toOrderListDTO(OrderList orderList);

    @IterableMapping(qualifiedByName = "toOrderListDTO")
    List<OrderListDTO> toOrderListDTO(List<OrderList> orderList);

    @Mapping(target = "vendor.products", ignore = true)
    @Mapping(target = "vendor.orderLists", ignore = true)
    @Mapping(target = "orders", qualifiedByName = "toOrderDTOWithoutOrderList")
    OrderListDTO toOrderListDTOWithOrders(OrderList orderList);

    OrderList fromOrderListDTO(OrderListDTO orderListDTO);
}
