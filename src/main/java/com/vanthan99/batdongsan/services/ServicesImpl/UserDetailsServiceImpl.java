package com.vanthan99.batdongsan.services.ServicesImpl;

import com.vanthan99.batdongsan.dto.UserDTO;
import com.vanthan99.batdongsan.entities.Role;
import com.vanthan99.batdongsan.entities.User;
import com.vanthan99.batdongsan.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("username not found with email: " + email);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<Role> roles = user.getRoles();
        for(Role role : roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().name()));
        }
        return UserDetailsImpl.build(user);
    }
}
