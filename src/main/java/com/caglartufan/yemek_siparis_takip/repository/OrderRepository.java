package com.caglartufan.yemek_siparis_takip.repository;

import com.caglartufan.yemek_siparis_takip.entity.Order;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<@NonNull Order, @NonNull Integer> {
    List<Order> findByOrderListId(Integer orderListId);

    Optional<Order> findByIdAndOrderListId(Integer orderId, Integer orderListId);
}
