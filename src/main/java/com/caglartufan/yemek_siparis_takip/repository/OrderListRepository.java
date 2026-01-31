package com.caglartufan.yemek_siparis_takip.repository;

import com.caglartufan.yemek_siparis_takip.entity.OrderList;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderListRepository extends JpaRepository<@NonNull OrderList, @NonNull Integer> {
    List<OrderList> findByVendorId(Integer vendorId);

    @Query("""
                SELECT ol FROM OrderList ol
                JOIN ol.orders o
                WHERE ol.id = :orderListId
                AND o.id IN :orderIds
            """)
    Optional<OrderList> findByIdWithOrdersByIds(Integer orderListId, List<Integer> orderIds);
}
