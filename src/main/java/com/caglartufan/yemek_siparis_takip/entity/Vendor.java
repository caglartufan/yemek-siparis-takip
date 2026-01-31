package com.caglartufan.yemek_siparis_takip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vendors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendor")
    private List<Product> products = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendor")
    private List<OrderList> orderLists = new ArrayList<>();

    public void addOrderList(OrderList orderList) {
        if (Objects.isNull(orderList)) return;

        orderLists.add(orderList);
        orderList.setVendor(this);
    }
}
