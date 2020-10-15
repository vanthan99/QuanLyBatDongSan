package com.vanthan99.batdongsan.services;

import com.vanthan99.batdongsan.entities.AccountReport;
import com.vanthan99.batdongsan.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface UserService {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<User> findAll(Pageable pageable);

    User findById(Long id);

    void save(User user);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(Long id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void blockById(Long id, AccountReport accountReport);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void unblockById(Long id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<User> searchUser(Pageable pageable, String keyword);

    boolean existsByEmail(String email);

    User findByEmail(String email);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<User> findAllAdmin(Pageable pageable);
}
