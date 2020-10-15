package com.vanthan99.batdongsan.webservices.payload.request;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

// Validate here
@Data
public class SignUpRequest {
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
    private Set<String> role;
}
