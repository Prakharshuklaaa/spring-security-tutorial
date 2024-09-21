package com.prakhar.client.entity;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
