package com.example.user_service;

import com.example.user_service.model.UserModel;
import com.example.user_service.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setUsername("KITIO Ange");
        userModel.setEmail("kt10@gmail.com");
        userModel.setPhone("+237 688900001");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userModel)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("KITIO Ange"));
    }

    @Test
    public void TestGetAllUser() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    }

    @Test
    public void TestGetUserById() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/users/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void TestUpdateUser() throws Exception{
        UserModel userModel = new UserModel();
        userModel.setUsername("KITIO Angela");
        userModel.setEmail("ktange10@gmail.com");
        userModel.setPhone("+237 688900001");

        mockMvc.perform(MockMvcRequestBuilders.put("/users/update/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userModel)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.username").value("KITIO Angela"))
        ;
    }

    @Test
    public void TestDeleteUser() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/delete/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}
