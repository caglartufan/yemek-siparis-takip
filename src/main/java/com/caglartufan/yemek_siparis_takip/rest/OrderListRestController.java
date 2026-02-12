package com.caglartufan.yemek_siparis_takip.rest;

import com.caglartufan.yemek_siparis_takip.dto.OrderDTO;
import com.caglartufan.yemek_siparis_takip.dto.OrderItemDTO;
import com.caglartufan.yemek_siparis_takip.dto.OrderListDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.order.DeleteOrdersDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.order.OrderCreateDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.order.OrderPatchDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.order_item.OrderItemCreateDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.order_item.OrderItemPatchDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.order_list.OrderListCreateDTO;
import com.caglartufan.yemek_siparis_takip.dto.request.order_list.OrderListPatchDTO;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order.*;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order_item.CreateOrderItemResponse;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order_item.GetOrderItemResponse;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order_item.ListOrderItemsResponse;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order_item.PatchOrderItemResponse;
import com.caglartufan.yemek_siparis_takip.response.rest_controller.order_list.*;
import com.caglartufan.yemek_siparis_takip.service.IOrderListService;
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
public class OrderListRestController {
    private final IOrderListService orderListService;

    /**
     * ORDER LIST ENDPOINTS
     */

    @GetMapping
    public ResponseEntity<@NonNull ListOrderListsResponse> listOrderLists(
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

    @GetMapping("/{orderListId}")
    public ResponseEntity<@NonNull GetOrderListResponse> getOrderList(@PathVariable Integer orderListId) {
        OrderListDTO orderListDTO = orderListService.findById(orderListId);
        GetOrderListResponse res = new GetOrderListResponse(orderListDTO);

        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<@NonNull CreateOrderListResponse> createOrderList(@Valid @RequestBody OrderListCreateDTO orderListCreateDTO) {
        OrderListDTO orderListDTO = orderListService.create(orderListCreateDTO);
        URI location = URI.create("/api/order-lists/" + orderListDTO.getId());
        CreateOrderListResponse res = new CreateOrderListResponse(orderListDTO);

        return ResponseEntity.created(location).body(res);
    }

    @PatchMapping("/{orderListId}")
    public ResponseEntity<@NonNull PatchOrderListResponse> patchOrderList(@PathVariable Integer orderListId,
                                                                          @Valid @RequestBody OrderListPatchDTO orderListPatchDTO) {
        OrderListDTO patchedOrderList = orderListService.patch(orderListId, orderListPatchDTO);
        PatchOrderListResponse res = new PatchOrderListResponse(patchedOrderList);

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{orderListId}")
    public ResponseEntity<@NonNull DeleteOrderListResponse> deleteOrderList(
            @PathVariable Integer orderListId
    ) {
        OrderListDTO orderList = orderListService.delete(orderListId);
        DeleteOrderListResponse res = new DeleteOrderListResponse(orderList);

        return ResponseEntity.ok(res);
    }

    /**
     * ORDER ENDPOINTS
     */

    @GetMapping("/{orderListId}/orders")
    public ResponseEntity<@NonNull ListOrdersResponse> listOrders(
            @PathVariable Integer orderListId
    ) {
        List<OrderDTO> orders = orderListService.listOrders(orderListId);
        ListOrdersResponse res = new ListOrdersResponse(orders);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/{orderListId}/orders/{orderId}")
    public ResponseEntity<@NonNull GetOrderResponse> getOrder(@PathVariable Integer orderListId,
                                                              @PathVariable Integer orderId) {
        OrderDTO orderDTO = orderListService.findOrderById(orderListId, orderId);
        GetOrderResponse res = new GetOrderResponse(orderDTO);

        return ResponseEntity.ok(res);
    }

    @PostMapping("/{orderListId}/orders")
    public ResponseEntity<@NonNull CreateOrderResponse> createOrder(
            @PathVariable Integer orderListId,
            @Valid @RequestBody OrderCreateDTO orderCreateDTO
    ) {
        OrderDTO orderDTO = orderListService.createOrder(orderListId, orderCreateDTO);
        URI location = URI.create("/api/order-lists/%d/orders/%d".formatted(orderListId, orderDTO.getId()));
        CreateOrderResponse res = new CreateOrderResponse(orderDTO);

        return ResponseEntity.created(location).body(res);
    }

    @PatchMapping("/{orderListId}/orders/{orderId}")
    public ResponseEntity<@NonNull PatchOrderResponse> patchOrder(@PathVariable Integer orderListId,
                                                                  @PathVariable Integer orderId,
                                                                  @Valid @RequestBody OrderPatchDTO orderPatchDTO) {
        OrderDTO patchedOrder = orderListService.patchOrder(orderListId, orderId, orderPatchDTO);
        PatchOrderResponse res = new PatchOrderResponse(patchedOrder);

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{orderListId}/orders")
    public ResponseEntity<@NonNull DeleteOrdersResponse> deleteOrdersWithIds(@PathVariable Integer orderListId,
                                                                             @Valid @RequestBody DeleteOrdersDTO deleteOrdersDTO) {
        List<OrderDTO> deletedOrders = orderListService.deleteOrdersWithIds(orderListId, deleteOrdersDTO);
        DeleteOrdersResponse res = new DeleteOrdersResponse(deletedOrders);

        return ResponseEntity.ok(res);
    }

    /**
     * ORDER ITEM ENDPOINTS
     */

    @GetMapping("/{orderListId}/orders/{orderId}/order-items")
    public ResponseEntity<@NonNull ListOrderItemsResponse> listOrderItems(@PathVariable Integer orderListId,
                                                                          @PathVariable Integer orderId) {
        List<OrderItemDTO> orderItems = orderListService.listOrderItems(orderListId, orderId);
        ListOrderItemsResponse res = new ListOrderItemsResponse(orderItems);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/{orderListId}/orders/{orderId}/order-items/{orderItemId}")
    public ResponseEntity<@NonNull GetOrderItemResponse> getOrderItem(@PathVariable Integer orderListId,
                                                                      @PathVariable Integer orderId,
                                                                      @PathVariable Integer orderItemId) {
        OrderItemDTO orderItem = orderListService.findOrderItemById(orderListId, orderId, orderItemId);
        GetOrderItemResponse res = new GetOrderItemResponse(orderItem);

        return ResponseEntity.ok(res);
    }

    @PostMapping("/{orderListId}/orders/{orderId}/order-items")
    public ResponseEntity<@NonNull CreateOrderItemResponse> create(@PathVariable Integer orderListId,
                                                                   @PathVariable Integer orderId,
                                                                   @Valid @RequestBody OrderItemCreateDTO orderItemCreateDTO) {
        OrderItemDTO orderItemDTO = orderListService.createOrderItem(orderListId, orderId, orderItemCreateDTO);
        CreateOrderItemResponse res = new CreateOrderItemResponse(orderItemDTO);
        URI location = URI.create("/api/order-lists/%d/orders/%d/order-items/%d".formatted(orderListId, orderId, 1));

        return ResponseEntity.created(location).body(res);
    }

    @PatchMapping("/{orderListId}/orders/{orderId}/order-items/{orderItemId}")
    public ResponseEntity<@NonNull PatchOrderItemResponse> patchOrderItem(@PathVariable Integer orderListId,
                                                                          @PathVariable Integer orderId,
                                                                          @PathVariable Integer orderItemId,
                                                                          @Valid @RequestBody OrderItemPatchDTO orderItemPatchDTO) {

        OrderItemDTO patchedOrderItem = orderListService.patchOrderItem(orderListId, orderId, orderItemId, orderItemPatchDTO);
        PatchOrderItemResponse res = new PatchOrderItemResponse(patchedOrderItem);

        return ResponseEntity.ok(res);
    }
}
