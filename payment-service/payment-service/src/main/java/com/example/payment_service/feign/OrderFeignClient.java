package com.example.payment_service.feign;

import com.example.payment_service.dto.OrderDTO;
import com.example.payment_service.dto.UpdateOrderStatutsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service")
public interface OrderFeignClient {

    @GetMapping("/order/{orderId}")
    OrderDTO getOrderById(@PathVariable int orderId);

    @PutMapping("/order/{orderId/statuts}")
    void updateOrderStatusByFacture (@PathVariable int orderId, @RequestBody UpdateOrderStatutsDTO dto);
}
