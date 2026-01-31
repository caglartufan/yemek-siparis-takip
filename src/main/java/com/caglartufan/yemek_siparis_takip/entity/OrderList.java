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
    private Integer id;

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
        if (Objects.isNull(order)) return;

        // Add the order and build relationship
        orders.add(order);
        order.setOrderList(this);

        // Update total price
        applyDelta(order.getTotalPrice());
    }

    public List<Order> removeOrders(List<Order> orders) {
        List<Order> removedOrders = new ArrayList<>();

        if (Objects.isNull(orders) || orders.isEmpty()) return removedOrders;

        // Define a BigDecimal to store amount to subtract from the totalPrice (must be negative or zero)
        AtomicReference<BigDecimal> totalToSubtract = new AtomicReference<>(BigDecimal.ZERO);

        orders.forEach(order -> {
            // If order is null or could not be removed (maybe it didn't exist), do nothing
            if (Objects.isNull(order) || !this.orders.remove(order)) return;

            // Subtract order's totalPrice from current subtraction amount
            totalToSubtract.set(totalToSubtract.get().subtract(order.getTotalPrice()));

            // Break the relationship
            order.setOrderList(null);

            // Add the order to the removedOrders
            removedOrders.add(order);
        });

        applyDelta(totalToSubtract.get());

        return removedOrders;
    }

    public List<Order> removeOrdersWithIds(List<Integer> orderIds) {
        List<Order> removedOrders = new ArrayList<>();

        if (Objects.isNull(orderIds) || orderIds.isEmpty()) return removedOrders;

        BigDecimal totalToSubtract = BigDecimal.ZERO;
        Set<Integer> orderIdsSet = new HashSet<>(orderIds);

        Iterator<Order> iterator = this.orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();

            // If order is not supposed to be removed, continue
            if (!orderIdsSet.contains(order.getId())) continue;

            // Remove the order and break the relationship
            iterator.remove();
            order.setOrderList(null);

            // Subtract order's totalPrice from current subtraction amount
            totalToSubtract = totalToSubtract.subtract(order.getTotalPrice());

            // Add the order to the removedOrders
            removedOrders.add(order);
        }

        applyDelta(totalToSubtract);

        return removedOrders;
    }

    public void addOrderItem(Integer orderId, OrderItem orderItem) {
        if (Objects.isNull(orderId) || Objects.isNull(orderItem)) return;

        Order order = findOrder(orderId);

        if (Objects.isNull(order)) return;

        BigDecimal delta = order.addOrderItem(orderItem);

        applyDelta(delta);
    }

    public void changeOrderItemQuantity(Integer orderId, Integer orderItemId, Integer quantity) {
        if (Objects.isNull(orderId) || Objects.isNull(orderItemId) || Objects.isNull(quantity)) return;

        Order order = findOrder(orderId);

        if (Objects.isNull(order)) return;

        BigDecimal delta = order.changeOrderItemQuantity(orderItemId, quantity);

        applyDelta(delta);
    }

    public void changeOrderItemPortion(Integer orderId, Integer orderItemId, BigDecimal portion) {
        if (Objects.isNull(orderId) || Objects.isNull(orderItemId) || Objects.isNull(portion)) return;

        Order order = findOrder(orderId);

        if (Objects.isNull(order)) return;

        BigDecimal delta = order.changeOrderItemPortion(orderItemId, portion);

        applyDelta(delta);
    }

    public void changeOrderItemProduct(Integer orderId, Integer orderItemId, Product product) {
        if (Objects.isNull(orderId) || Objects.isNull(orderItemId) || Objects.isNull(product)) return;

        Order order = findOrder(orderId);

        if (Objects.isNull(order)) return;

        BigDecimal delta = order.changeOrderItemProduct(orderItemId, product);

        applyDelta(delta);
    }

    public void applyDelta(BigDecimal delta) {
        totalPrice = totalPrice.add(delta);
    }

    private Order findOrder(Integer orderId) {
        if (Objects.isNull(orderId)) return null;

        return orders.stream()
                .filter(order -> order.getId().equals(orderId))
                .findAny()
                .orElse(null);
    }
}
