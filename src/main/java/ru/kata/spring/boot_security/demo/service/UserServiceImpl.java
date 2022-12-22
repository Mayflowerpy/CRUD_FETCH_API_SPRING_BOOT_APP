package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(long id) {
        Optional<User> userById = userRepository.findById(id);
        if (userById.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User with id %s not found", id));
        } else {
            return userById.get();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public void addUser(User newUser) {
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        userRepository.saveAndFlush(newUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(long id, User userForUpdate) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User updateUser = user.get();
            updateUser.setId(id);
            updateUser.setName(userForUpdate.getName());
            updateUser.setLastName(userForUpdate.getLastName());
            updateUser.setAge(userForUpdate.getAge());
            updateUser.setMail(userForUpdate.getMail());
            updateUser.setUsername(updateUser.getUsername());
            updateUser.setPassword(new BCryptPasswordEncoder().encode(userForUpdate.getPassword()));
            updateUser.setRoles(userForUpdate.getRoles());
            userRepository.saveAndFlush(updateUser);
        } else {
            throw new UsernameNotFoundException(String.format("User with id %s not found", id));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = getUserByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        } else {
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), user.get().getAuthorities());
        }
    }
}