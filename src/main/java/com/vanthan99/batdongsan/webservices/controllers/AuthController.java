package com.vanthan99.batdongsan.webservices.controllers;


import com.vanthan99.batdongsan.configs.securities.JwtUtils;
import com.vanthan99.batdongsan.entities.ERole;
import com.vanthan99.batdongsan.entities.Role;
import com.vanthan99.batdongsan.entities.User;
import com.vanthan99.batdongsan.repositories.RoleRepository;
import com.vanthan99.batdongsan.services.ServicesImpl.UserDetailsImpl;
import com.vanthan99.batdongsan.services.UserService;
import com.vanthan99.batdongsan.webservices.payload.request.SignInRequest;
import com.vanthan99.batdongsan.webservices.payload.request.SignUpRequest;
import com.vanthan99.batdongsan.webservices.payload.response.ApiResponse;
import com.vanthan99.batdongsan.webservices.payload.response.JwtResponse;
import com.vanthan99.batdongsan.webservices.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@RequestBody SignInRequest signInRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken((UserDetails) authentication.getPrincipal());

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<Role> roles = userDetails.getAuthorities().stream()
                .map(item -> roleRepository.findByRoleName(ERole.valueOf(item.getAuthority())).get())
                .collect(Collectors.toSet());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getName(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        // Create new user's account

//         User user = new User(signUpRequest.getName(),
//                signUpRequest.getEmail(),
//                signUpRequest.getAddress(),
//                signUpRequest.getPhoneNumber(),
//                encoder.encode(signUpRequest.getPassword()));
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setAddress(signUpRequest.getAddress());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByRoleName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false,"You are not signing!"));
        }
        User currentUser = userService.findByEmail(userDetails.getUsername());
        return ResponseEntity.ok().body(currentUser);
    }


}
