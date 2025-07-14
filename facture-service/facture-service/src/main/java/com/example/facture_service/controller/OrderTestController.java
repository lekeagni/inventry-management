package com.example.facture_service.controller;

import com.example.facture_service.dto.OrderDTO;
import com.example.facture_service.feign.OrderFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderTestController {

    private final OrderFeignClient orderFeignClient;

    public OrderTestController(OrderFeignClient orderFeignClient) {

        this.orderFeignClient = orderFeignClient;
    }

    @GetMapping("/order/{orderId}")
    @Operation(summary = "get order by id", description = "get the order in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get order successful"),
            @ApiResponse(responseCode = "400", description = "failed to get order"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<OrderDTO> get(@PathVariable int orderId){
        return ResponseEntity.status(HttpStatus.OK).body(this.orderFeignClient.getOrderById(orderId));
    }
}
