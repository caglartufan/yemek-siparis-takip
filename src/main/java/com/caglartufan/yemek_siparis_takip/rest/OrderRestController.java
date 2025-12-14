package com.caglartufan.yemek_siparis_takip.rest;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.DeleteOrdersDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.OrderCreateDTO;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order.CreateOrderResponse;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order.DeleteOrdersResponse;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order.GetOrderResponse;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order.ListOrdersResponse;
import com.caglartufan.yemek_siparis_takip.service.IOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/order-lists")
@RequiredArgsConstructor
public class OrderRestController {
    private final IOrderService orderService;

    @GetMapping("/{orderListId}/orders")
    public ResponseEntity<@NonNull ListOrdersResponse> list(
            @PathVariable Integer orderListId
    ) {
        List<OrderDTO> orders = orderService.list(orderListId);
        ListOrdersResponse res = new ListOrdersResponse(orders);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/{orderListId}/orders/{id}")
    public ResponseEntity<@NonNull GetOrderResponse> get(@PathVariable Integer orderListId, @PathVariable Integer id) {
        OrderDTO orderDTO = orderService.findById(orderListId, id);
        GetOrderResponse res = new GetOrderResponse(orderDTO);

        return ResponseEntity.ok(res);
    }

    @PostMapping("/{orderListId}/orders")
    public ResponseEntity<@NonNull CreateOrderResponse> create(
            @PathVariable Integer orderListId,
            @Valid @RequestBody OrderCreateDTO dto
    ) {
        OrderDTO orderDTO = orderService.create(orderListId, dto);
        URI location = URI.create("/api/order-lists/" + orderListId + "/orders/" + orderDTO.getId());
        CreateOrderResponse res = new CreateOrderResponse(orderDTO);

        return ResponseEntity.created(location).body(res);
    }

    @DeleteMapping("/{orderListId}/orders")
    public ResponseEntity<@NonNull DeleteOrdersResponse> deleteWithIds(@PathVariable Integer orderListId, @Valid @RequestBody DeleteOrdersDTO dto) {
        List<OrderDTO> removedOrders = orderService.deleteWithIds(orderListId, dto.getOrderIds());
        DeleteOrdersResponse res = new DeleteOrdersResponse(removedOrders);

        return ResponseEntity.ok(res);
    }
}
