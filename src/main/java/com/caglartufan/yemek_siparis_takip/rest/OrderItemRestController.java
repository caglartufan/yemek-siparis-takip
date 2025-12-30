package com.caglartufan.yemek_siparis_takip.rest;

import com.caglartufan.yemek_siparis_takip.dto.OrderItemDTO;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order_items.ListOrderItemsResponse;
import com.caglartufan.yemek_siparis_takip.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-lists")
@RequiredArgsConstructor
public class OrderItemRestController {
    private final OrderItemService orderItemService;

    @GetMapping("/{orderListId}/orders/{orderId}/order-items")
    public ResponseEntity<ListOrderItemsResponse> list(@PathVariable Integer orderListId, @PathVariable Integer orderId) {
        List<OrderItemDTO> orderItems = orderItemService.list(orderListId, orderId);
        ListOrderItemsResponse res = new ListOrderItemsResponse(orderItems);

        return ResponseEntity.ok(res);
    }
}
