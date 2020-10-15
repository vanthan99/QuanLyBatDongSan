package com.vanthan99.batdongsan.repositories;

import com.vanthan99.batdongsan.entities.ERole;
import com.vanthan99.batdongsan.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(ERole name);

    Role findByRoleNameIs(ERole user);
}
