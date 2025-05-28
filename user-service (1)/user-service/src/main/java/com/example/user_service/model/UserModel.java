package com.example.user_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = " users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "USERNAME",nullable = false)
    private String username;

    @Column(name = "PHONE",nullable = false)
    private String phone;

    @Column(name = "EMAIL",nullable = false)
    private String email;
}
