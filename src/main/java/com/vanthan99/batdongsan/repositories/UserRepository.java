package com.vanthan99.batdongsan.repositories;

import com.vanthan99.batdongsan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    Boolean existsByEmail(String email);
}
