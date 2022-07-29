package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();

    void add(User user);

    void update(User user, int id);

    void delete(int id);

    User findByUsername(String username);
}
