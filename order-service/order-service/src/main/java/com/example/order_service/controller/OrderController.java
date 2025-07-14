package com.example.order_service.controller;

import com.example.order_service.dto.OrderDTO;
import com.example.order_service.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Tag(name = "test orders", description = "APIs REST test order and interactive documentation with swagger")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    @Operation(summary = "add order", description = "save order in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "saved order successful"),
            @ApiResponse(responseCode = "400", description = "failed to create order"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.orderService.createOrder(dto));
    }

    @GetMapping()
    @Operation(summary = "get all orders", description = "get orders in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get order successful"),
            @ApiResponse(responseCode = "400", description = "failed to get orders"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<List<OrderDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.orderService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "get order by id", description = "get the order in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get order successful"),
            @ApiResponse(responseCode = "400", description = "failed to get order"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<OrderDTO> getOrder(@PathVariable int orderId){
        return ResponseEntity.status(HttpStatus.OK).body(this.orderService.getOrderById(orderId));
    }

    @PutMapping("/update/{orderId}")
    @Operation(summary = "update order", description = "update order in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updated order successful"),
            @ApiResponse(responseCode = "400", description = "failed to update order"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<OrderDTO> update(@PathVariable int orderId, @RequestBody OrderDTO dto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.orderService.updateOrder(orderId, dto));
    }

    @DeleteMapping("/delete/{orderId}")
    @Operation(summary = "delete order", description = "delete order in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted order successful"),
            @ApiResponse(responseCode = "400", description = "failed to delete order"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<Boolean> delete(@PathVariable int orderId){
        boolean isDeleted = this.orderService.deleteOrder(orderId);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
