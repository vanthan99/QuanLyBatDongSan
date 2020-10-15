package com.vanthan99.batdongsan.webservices.payload.request;


import com.vanthan99.batdongsan.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminAccountRequest {
    private String email;
    private String name;
    private Role role;
}
