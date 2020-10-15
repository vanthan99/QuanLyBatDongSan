package com.vanthan99.batdongsan.webservices.payload.response;


import com.vanthan99.batdongsan.entities.Role;
import lombok.*;

import java.util.Set;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer ";
    private Long id;
    private String email;
    private String name;
    private Set<Role> roles;

    public JwtResponse(String token, Long id, String email, String name, Set<Role> roles) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.name = name;
        this.roles = roles;
    }
}
