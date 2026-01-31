package com.caglartufan.yemek_siparis_takip.repository;

import com.caglartufan.yemek_siparis_takip.entity.Order;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<@NonNull Order, @NonNull Integer> {
    @Query("""
                SELECT o FROM Order o
                WHERE o.orderList.id = :orderListId
            """)
    Optional<List<Order>> findOrdersOfOrderList(Integer orderListId);

    @Query("""
            SELECT o FROM Order o
            WHERE o.orderList.id = :orderListId
            AND o.id = :orderId
            """)
    Optional<Order> findOrderOfOrderList(Integer orderListId, Integer orderId);
}
