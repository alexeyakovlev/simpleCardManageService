package ru.yakovlev.simplerestapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yakovlev.simplerestapi.models.User;
import ru.yakovlev.simplerestapi.services.UserService;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by alexi on 28.06.2025
 */
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (userService.createUser(user)) {
            model.addAttribute("errorMessage", "User with email " + user.getEmail() + " already exists");
            return "registration";
        }
        return "redirect:/login";
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
