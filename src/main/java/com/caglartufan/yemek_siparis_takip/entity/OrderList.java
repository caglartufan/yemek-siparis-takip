package com.caglartufan.yemek_siparis_takip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Entity
@Table(name = "order_lists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderList", orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        if (order == null) return;

        this.getOrders().add(order);
        this.totalPrice = this.totalPrice.add(order.getTotalPrice());
        order.setOrderList(this);
    }

    public List<Order> removeOrders(List<Order> orders) {
        List<Order> removedOrders = new ArrayList<>();

        if (orders == null || orders.isEmpty()) return removedOrders;

        AtomicReference<BigDecimal> totalToSubtract = new AtomicReference<>(BigDecimal.ZERO);

        orders.forEach(order -> {
            if (order == null || !this.orders.remove(order)) return;

            totalToSubtract.set(totalToSubtract.get().add(order.getTotalPrice()));
            order.setOrderList(null);
            removedOrders.add(order);
        });

        this.totalPrice = this.totalPrice.subtract(totalToSubtract.get());

        return removedOrders;
    }

    public List<Order> removeOrdersWithIds(List<Integer> orderIds) {
        List<Order> removedOrders = new ArrayList<>();

        if (orderIds == null || orderIds.isEmpty()) return removedOrders;

        BigDecimal totalToSubtract = BigDecimal.ZERO;
        Set<Integer> orderIdsSet = new HashSet<>(orderIds);

        Iterator<Order> iterator = this.orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();

            if (!orderIdsSet.contains(order.getId())) continue;

            iterator.remove();
            totalToSubtract = totalToSubtract.add(order.getTotalPrice());
            order.setOrderList(null);
            removedOrders.add(order);
        }

        this.totalPrice = this.totalPrice.subtract(totalToSubtract);

        return removedOrders;
    }
}
