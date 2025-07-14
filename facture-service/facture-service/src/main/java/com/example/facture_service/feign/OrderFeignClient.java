package com.example.facture_service.feign;

import com.example.facture_service.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service")
public interface OrderFeignClient {

    @GetMapping("/order/{orderId}")
    OrderDTO getOrderById(@PathVariable int orderId);
}
