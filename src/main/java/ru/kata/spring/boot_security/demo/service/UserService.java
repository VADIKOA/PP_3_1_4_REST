package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    User add(User user);

    void delete(int id);

    void update(User user, int id);

    @Override
    User loadUserByUsername(String login) throws UsernameNotFoundException;
}
