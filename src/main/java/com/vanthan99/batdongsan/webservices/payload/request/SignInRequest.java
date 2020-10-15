package com.vanthan99.batdongsan.webservices.payload.request;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}