package com.vanthan99.batdongsan.services.ServicesImpl;

import com.vanthan99.batdongsan.entities.AccountReport;
import com.vanthan99.batdongsan.entities.User;
import com.vanthan99.batdongsan.repositories.UserRepository;
import com.vanthan99.batdongsan.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void blockById(Long id, AccountReport accountReport) {
        User user = userRepository.getOne(id);
        user.setStatus(false);
        user.addAccountReport(accountReport);
        userRepository.save(user);
    }

    @Override
    public void unblockById(Long id) {
        User user = userRepository.getOne(id);
        user.setStatus(true);
        userRepository.save(user);
    }

    @Override
    public Page<User> searchUser(Pageable pageable, String keyword) {
//        return userRepository.findAll(
//                Specification
//                        .where(UserSpecification.textInAllColumns(keyword, Arrays.asList("email", "phoneNumber", "name"))
//                                .and(UserSpecification.isUser()))
//                , pageable);
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Page<User> findAllAdmin(Pageable pageable) {
//        return userRepository.findAll(
//                Specification.where(UserSpecification.isAdmin()),
//                pageable
//        );
        return null;
    }
}
