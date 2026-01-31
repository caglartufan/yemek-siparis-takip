package com.caglartufan.yemek_siparis_takip.repository;

import com.caglartufan.yemek_siparis_takip.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query("""
                SELECT oi FROM OrderItem oi
                JOIN oi.order o
                JOIN o.orderList ol
                WHERE ol.id = :orderListId
                AND o.id = :orderId
            """)
    Optional<List<OrderItem>> findOrderItemsOfOrderOfOrderList(Integer orderListId, Integer orderId);

    @Query("""
                SELECT oi FROM OrderItem oi
                JOIN oi.order o
                JOIN o.orderList ol
                WHERE ol.id = :orderListId
                AND o.id = :orderId
                AND oi.id = :orderItemId
            """)
    Optional<OrderItem> findOrderItemOfOrderOfOrderList(Integer orderListId, Integer orderId, Integer orderItemId);
}
