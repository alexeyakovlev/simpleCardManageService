package ru.yakovlev.simplerestapi.controllers;

import org.springframework.web.bind.annotation.*;
import ru.yakovlev.simplerestapi.models.User;
import ru.yakovlev.simplerestapi.services.UserService;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by alexi on 28.06.2025
 */
@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> allUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable Long id,
                           @RequestParam String email,
                           @RequestParam String name,
                           @RequestParam LocalDate dateOfBirth) {
        userService.updateUser(id, email, name, dateOfBirth);
    }
}
