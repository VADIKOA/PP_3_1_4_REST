package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    @Transactional
    public User add(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.add(user);
        return user;
    }

    @Override
    @Transactional
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    @Transactional
    public void update(User user, int id) {
        user.setId(id);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.update(user, id);
    }

    @Override
    @Transactional
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        user.getAuthorities().size();
        return user;
    }
}
