package com.vanthan99.batdongsan.webservices.controllers;


import com.vanthan99.batdongsan.entities.ERole;
import com.vanthan99.batdongsan.entities.Role;
import com.vanthan99.batdongsan.entities.User;
import com.vanthan99.batdongsan.repositories.RoleRepository;
import com.vanthan99.batdongsan.services.UserService;
import com.vanthan99.batdongsan.webservices.payload.request.AdminAccountRequest;
import com.vanthan99.batdongsan.webservices.payload.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/admins")
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    private static final String NOT_FOUND_USER = "Cannot find this user!";
    private final String DEFAULT_PASSWORD = "admin123456";
    private final String CREAT_ACCOUNT_SUCCESS = "Create admin account successfully!";
    private final String BLOCK_ACCOUNT_SUCCESS = "Block admin successfully!";

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> findAllAdmins(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(userService.findAllAdmin(pageable), HttpStatus.OK);
    }


    @PostMapping("/{id}/block")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> blockAdmin(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse(false, NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }
        user.setStatus(false);
        userService.save(user);
        return ResponseEntity.ok().body(new ApiResponse(true, BLOCK_ACCOUNT_SUCCESS));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createAdminAccount(@RequestBody AdminAccountRequest adminAccountRequest) {
        User user = new User();
        user.setEmail(adminAccountRequest.getEmail());
        user.setName(adminAccountRequest.getName());
        user.setAddress("");
        user.setPhoneNumber("");
        user.setPassword(encoder.encode(DEFAULT_PASSWORD));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByRoleName(ERole.ROLE_USER).get());
        roles.add(adminAccountRequest.getRole());
        user.setRoles(roles);
        userService.save(user);
        return ResponseEntity.ok().body(new ApiResponse(true, CREAT_ACCOUNT_SUCCESS));
    }

    @PostMapping("/{id}/unblock")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> unblockAdmin(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse(false, NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }
        user.setStatus(true);
        userService.save(user);
        return ResponseEntity.ok().body(new ApiResponse(true, "Unblock admin successfully!"));
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteAdmin(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse(false, NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }
        user.setRoles(null);
        userService.save(user);
        userService.deleteById(user.getId());
        return ResponseEntity.ok().body(new ApiResponse(true, "Delete admin successfully!"));
    }


}
