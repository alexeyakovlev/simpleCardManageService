package ru.yakovlev.simplerestapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.yakovlev.simplerestapi.models.User;
import ru.yakovlev.simplerestapi.services.UserService;

import java.util.List;

/**
 * Created by alexi on 03.07.2025
 */
@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("cards", user.getCards());
        return "user-info";
    }
}
