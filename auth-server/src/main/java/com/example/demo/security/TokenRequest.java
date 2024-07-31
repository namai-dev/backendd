package com.example.demo.security;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRequest {
    public String token;
}
