package com.example.user_service.controller;

import com.example.user_service.dto.UserDTO;
import com.example.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@Tag(name = "APIs REST test user and interactive documentation with swagger")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    @Operation(summary = "add user", description = "save user in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "saved user successful"),
            @ApiResponse(responseCode = "400", description = "failed to create user"),
            @ApiResponse(responseCode = "500", description = "error server")
    })

    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(dto));
    }

    @GetMapping()
    @Operation(summary = "get all users", description = "get users in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get user successful"),
            @ApiResponse(responseCode = "400", description = "failed to get all users"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    @Operation(summary = "get the user", description = "get one user in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get user successful"),
            @ApiResponse(responseCode = "400", description = "failed to get user"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<UserDTO> getUser(@PathVariable int userId){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getUserById(userId));
    }

    @PutMapping("/update/{userId}")
    @Operation(summary = "update user", description = "update user wich exist in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updated user successful"),
            @ApiResponse(responseCode = "400", description = "failed to update user"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<UserDTO> update(@PathVariable int userId, @RequestBody UserDTO dto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.userService.updateUser(userId, dto));
    }

    @DeleteMapping("/delete/{userId}")
    @Operation(summary = "delete user", description = "delete user wich exist in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted user successful"),
            @ApiResponse(responseCode = "400", description = "failed to delete user"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<Boolean> delete(@PathVariable int userId){
        boolean exist = this.userService.deleteUser(userId);
        if (exist){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
