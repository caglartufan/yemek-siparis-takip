package com.caglartufan.yemek_siparis_takip.mapper;

import com.caglartufan.yemek_siparis_takip.dto.OrderItemDTO;
import com.caglartufan.yemek_siparis_takip.entity.OrderItem;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    @Named("toOrderItemDTO")
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product.vendor", ignore = true)
    @Mapping(target = "product.orderItems", ignore = true)
    OrderItemDTO toOrderItemDTO(OrderItem orderItem);

    @IterableMapping(qualifiedByName = "toOrderItemDTO")
    List<OrderItemDTO> toOrderItemDTOWithOrderItems(List<OrderItem> orderItemList);
}
