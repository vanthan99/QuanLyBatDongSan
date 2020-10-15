package com.vanthan99.batdongsan.services;

import com.vanthan99.batdongsan.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String userName);
    List<UserDTO> findAll();
}
