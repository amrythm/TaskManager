package com.acko.demo.authentication.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
