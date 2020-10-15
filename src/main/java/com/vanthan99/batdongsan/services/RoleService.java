package com.vanthan99.batdongsan.services;

import com.vanthan99.batdongsan.entities.Role;

public interface RoleService {
    Iterable<Role> findAll();

    Role findById(Long id);

    void save(Role role);

    void deleteById(Long id);
}
