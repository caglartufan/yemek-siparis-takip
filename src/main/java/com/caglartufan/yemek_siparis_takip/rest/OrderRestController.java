package com.caglartufan.yemek_siparis_takip.rest;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order.ListOrdersResponse;
import com.caglartufan.yemek_siparis_takip.service.OrderService;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-lists")
public class OrderRestController {
    private OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderListId}/orders")
    public ResponseEntity<@NonNull ListOrdersResponse> list(
            @PathVariable Integer orderListId
    ) {
        List<OrderDTO> orders = orderService.list(orderListId);
        ListOrdersResponse res = new ListOrdersResponse(orders);

        return ResponseEntity.ok(res);
    }
}
