package com.example.Back.Service;

import com.example.Back.security.user.User;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface UserService {
    void addUser(User user);

    List<User> getUsers();
    List<User> getUsersByAdmin();

    User getUser(Long id);

    void updateUser(Long id, User user);


    void deleteUser(Long id);
    void enableUser(Long id);
    void disableUser(Long id);

    Authentication Authenticate(String name, String password);
}
