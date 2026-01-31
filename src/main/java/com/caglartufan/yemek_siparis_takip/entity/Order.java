package com.caglartufan.yemek_siparis_takip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ordered_by")
    private String orderedBy;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
    })
    @JoinColumn(name = "order_list_id")
    private OrderList orderList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public BigDecimal addOrderItem(OrderItem orderItem) {
        if (Objects.isNull(orderItem)) return BigDecimal.ZERO;

        // Add the orderItem and build relationship
        orderItems.add(orderItem);
        orderItem.setOrder(this);

        BigDecimal delta = orderItem.getTotalPrice();

        applyDelta(delta);

        return delta;
    }

    public BigDecimal changeOrderItemQuantity(Integer orderItemId, Integer quantity) {
        if (Objects.isNull(orderItemId) || Objects.isNull(quantity)) return BigDecimal.ZERO;

        OrderItem orderItem = findOrderItem(orderItemId);

        if (Objects.isNull(orderItem)) return BigDecimal.ZERO;

        BigDecimal delta = orderItem.changeQuantity(quantity);

        applyDelta(delta);

        return delta;
    }

    public BigDecimal changeOrderItemPortion(Integer orderItemId, BigDecimal portion) {
        if (Objects.isNull(orderItemId) || Objects.isNull(portion)) return BigDecimal.ZERO;

        OrderItem orderItem = findOrderItem(orderItemId);

        if (Objects.isNull(orderItem)) return BigDecimal.ZERO;

        BigDecimal delta = orderItem.changePortion(portion);

        applyDelta(delta);

        return delta;
    }

    public BigDecimal changeOrderItemProduct(Integer orderItemId, Product product) {
        if (Objects.isNull(orderItemId) || Objects.isNull(product)) return BigDecimal.ZERO;

        OrderItem orderItem = findOrderItem(orderItemId);

        if (Objects.isNull(orderItem)) return BigDecimal.ZERO;

        BigDecimal delta = orderItem.changeProduct(product);

        applyDelta(delta);

        return delta;
    }

    public void applyDelta(BigDecimal delta) {
        totalPrice = totalPrice.add(delta);
    }

    private OrderItem findOrderItem(Integer orderItemId) {
        if (Objects.isNull(orderItemId)) return null;

        return orderItems.stream()
                .filter(orderItem -> orderItem.getId().equals(orderItemId))
                .findAny()
                .orElse(null);
    }
}
