package ru.yakovlev.simplerestapi.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovlev.simplerestapi.models.Role;
import ru.yakovlev.simplerestapi.models.User;
import ru.yakovlev.simplerestapi.repositories.UserRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by alexi on 28.06.2025
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
        return optionalUser.get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new IllegalStateException("Пользователь с таким email уже существует");
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("saving new user with email: " + user.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new IllegalStateException("Пользователь с id:" + id + " не существует");
        }
        log.info("deleting user with id: " + id);
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateUser(Long id, String email, String name, LocalDate dateOfBirth) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new IllegalStateException("Пользователь с id:" + id + " не существует");
        }
        User user = optionalUser.get();
        if (email != null && !email.equals(user.getEmail())) {
            Optional<User> foundByEmail = userRepository.findByEmail(email);
            if (optionalUser.isPresent()) {
                throw new IllegalStateException("Пользователь с таким email уже существует");
            }
            user.setEmail(email);
        }
        if (name != null && !name.equals(user.getEmail())){
            user.setName(name);
        }
        user.setDateOfBirth(dateOfBirth);
        log.info("updating user with id: " + id);
        userRepository.save(user);
    }

    public void banUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new IllegalStateException("User with id " + id + " not found");
        }
        User user = optionalUser.get();
        if (user.getRoles().contains(Role.ROLE_ADMIN)){
            throw new IllegalStateException("You can't ban user admin");
        }
        if(user.isActive()){
            user.setActive(false);
            log.info("ban user with id: " + id);
        } else {
            user.setActive(true);
            log.info("unban user with id: " + id);
        }
        userRepository.save(user);
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for(String key : form.keySet()){
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
        log.info("changing user roles: " + user.getRoles() + " with id: " + user.getId());
    }
}
