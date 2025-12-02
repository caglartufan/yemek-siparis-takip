package com.caglartufan.yemek_siparis_takip.rest;

import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.OrderListCreateDTO;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order_list.CreateOrderListResponse;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order_list.DeleteOrderListResponse;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order_list.ListOrderListsResponse;
import com.caglartufan.yemek_siparis_takip.service.OrderListService;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/order-lists")
public class OrderListRestController {
    private final OrderListService orderListService;

    public OrderListRestController(OrderListService orderListService) {
        this.orderListService = orderListService;
    }

    @GetMapping
    public ResponseEntity<@NonNull ListOrderListsResponse> list(
            @RequestParam(required = false, name = "vendor_id") Integer vendorId
    ) {
        List<OrderListDTO> orderListDTOs;

        if (vendorId != null) {
            orderListDTOs = orderListService.listByVendor(vendorId);
        } else {
            orderListDTOs = orderListService.list();
        }

        ListOrderListsResponse res = new ListOrderListsResponse(orderListDTOs);

        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<@NonNull CreateOrderListResponse> create(@Valid @RequestBody OrderListCreateDTO dto) {
        OrderListDTO orderListDTO = orderListService.create(dto);
        URI location = URI.create("/api/order-lists/" + orderListDTO.getId());
        CreateOrderListResponse res = new CreateOrderListResponse(orderListDTO);

        return ResponseEntity.created(location).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<@NonNull DeleteOrderListResponse> delete(
            @PathVariable Integer id
    ) {
        OrderListDTO orderList = orderListService.delete(id);
        DeleteOrderListResponse res = new DeleteOrderListResponse(orderList);

        return ResponseEntity.ok(res);
    }
}
