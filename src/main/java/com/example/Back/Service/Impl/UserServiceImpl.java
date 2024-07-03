package com.example.Back.Service.Impl;
import com.example.Back.Service.UserService;
import com.example.Back.security.user.Role;
import com.example.Back.security.user.User;
import com.example.Back.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByAdmin() {
        return userRepository.findAllByRole(Role.USER);
    }

    @Override
    public User getUser(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid user id " + id));

        return user;
    }

    @Override
    public void updateUser(Long id, User user) {
        // Check whether the user is in the database or not
        userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user id " + id));

        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        // Check whether the user is in the database or not
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user id " + id));
        userRepository.delete(user);
    }

    @Override
    public void enableUser(Long id) {
        User user = this.userRepository.findById(id).get();
        user.setAccountLocked(false);
        this.userRepository.save(user);
    }

    @Override
    public void disableUser(Long id) {
        User user = this.userRepository.findById(id).get();
        user.setAccountLocked(true);
        this.userRepository.save(user);
    }

    @Override
    public Authentication Authenticate(String name, String password) {
        return null;
    }

    public User getAuthenticatedUser() {
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.userRepository.findByEmail(authenticatedUserEmail).orElseThrow(() -> new UsernameNotFoundException("Invalid user email " + authenticatedUserEmail));
    }
}
