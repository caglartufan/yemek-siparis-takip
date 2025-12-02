package com.caglartufan.yemek_siparis_takip.repository;

import com.caglartufan.yemek_siparis_takip.entity.OrderList;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderListRepository extends JpaRepository<@NonNull OrderList, @NonNull Integer> {
    // Find by vendor id
    List<OrderList> findByVendorId(Integer vendorId);
}
