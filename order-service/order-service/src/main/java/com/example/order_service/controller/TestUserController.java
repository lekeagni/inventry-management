package com.example.order_service.controller;

import com.example.order_service.dto.UserDTO;
import com.example.order_service.feign.UserFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "test user", description = "APIs REST test user and  APIs interactive documentation with swagger")
public class TestUserController {

    private final UserFeignClient userFeignClient;

    public TestUserController(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @GetMapping("/users/{userId}")
    @Operation(summary = "get user by id", description = "get user in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get user successful"),
            @ApiResponse(responseCode = "400", description = "failed to get user"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userFeignClient.getUserById(userId));
    }

}
