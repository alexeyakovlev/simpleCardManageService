package ru.yakovlev.simplerestapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        return "user-info";
    }

    @PutMapping(path = "/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        return "redirect:/user-info";
    }
}
