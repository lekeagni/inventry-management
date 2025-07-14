package com.example.order_service;

import com.example.order_service.model.OrderModel;
import com.example.order_service.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class OrderIntegrationTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private OrderRepository orderRepository;

    @Test
    public void TestCreateOrder() throws Exception {

        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(1);
        orderModel.setProductId(2);
        orderModel.setTotal_amount(20000.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/order/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderModel)))
                .andExpect(status().isCreated());

    }

    @Test
    public void TestGetAllOrder() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/order")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void TestGetOrder() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/order/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void TestUpdateOrder() throws Exception{

        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(3);
        orderModel.setProductId(3);
        orderModel.setTotal_amount(25000.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/order/update/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderModel)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void TestDeleteOrder() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/order/delete/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}
