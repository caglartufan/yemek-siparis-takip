package com.caglartufan.yemek_siparis_takip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    public static void validateOrder(Order order) {
        if (Objects.isNull(order)) {
            throw new IllegalArgumentException("Sipariş boş olamaz!");
        }
    }

    public static void validateQuantity(Integer quantity, boolean allowNull) {
        boolean isNull = Objects.isNull(quantity);

        if (allowNull && isNull) {
            return;
        } else if (!allowNull && isNull) {
            throw new IllegalArgumentException("Adet boş olamaz.");
        }

        if (quantity < 1) {
            throw new IllegalArgumentException("Adet en az bir olmalıdır.");
        }
    }

    public static void validatePortion(BigDecimal portion, boolean allowNull) {
        boolean isNull = Objects.isNull(portion);

        if (allowNull && isNull) {
            return;
        } else if (!allowNull && isNull) {
            throw new IllegalArgumentException("Porsiyon boş olamaz.");
        }

        if (portion.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Porsiyon pozitif sayı olmalıdır!");
        }
    }

    public static void validateProduct(Product product) {
        if (Objects.isNull(product)) {
            throw new IllegalArgumentException("Sipariş öğesinin ürünü boş olamaz!");
        }
    }

    public static OrderItem createOrderItem(Order order, Integer quantity, BigDecimal portion, Product product) {
        // Validate parameters
        validateOrder(order);
        validateQuantity(quantity, true);
        validatePortion(portion, true);
        validateProduct(product);

        // Create OrderItem instance and set properties
        OrderItem orderItem = new OrderItem();

        orderItem.quantity = Objects.isNull(quantity) ? 1 : quantity;
        orderItem.portion = Objects.isNull(portion) ? BigDecimal.ONE : portion;
        orderItem.unitPrice = product.getPrice();
        orderItem.product = product;
        orderItem.order = order;

        // Recalculate and set orderItem's totalPrice
        orderItem.recalculateOrderItemTotalPrice();

        return orderItem;
    }

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity = 1;

    @Column(name = "portion", precision = 3, scale = 1, nullable = false)
    private BigDecimal portion = BigDecimal.ONE;

    @Column(name = "unit_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "total_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Setter
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public BigDecimal changeQuantity(Integer quantity) {
        validateQuantity(quantity, false);

        this.quantity = quantity;

        return recalculateOrderItemTotalPrice();
    }

    public BigDecimal changePortion(BigDecimal portion) {
        validatePortion(portion, false);

        this.portion = portion;

        return recalculateOrderItemTotalPrice();
    }

    public BigDecimal changeProduct(Product product) {
        validateProduct(product);

        this.product = product;
        unitPrice = product.getPrice();

        return recalculateOrderItemTotalPrice();
    }

    private BigDecimal recalculateOrderItemTotalPrice() {
        BigDecimal prevTotalPrice = totalPrice;

        if (Objects.isNull(quantity) || Objects.isNull(unitPrice) || Objects.isNull(portion)) {
            totalPrice = BigDecimal.ZERO;
            return totalPrice.subtract(prevTotalPrice);
        }

        totalPrice = unitPrice
                .multiply(BigDecimal.valueOf(quantity))
                .multiply(portion);

        return totalPrice.subtract(prevTotalPrice);
    }
}
