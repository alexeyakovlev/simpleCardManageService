package ru.yakovlev.simplerestapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ru.yakovlev.simplerestapi.models.Role;
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

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping(path = "admin/user/{user}")
    public String showProfile(@AuthenticationPrincipal @PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        //will add cards
        return "user-info";
    }

    @DeleteMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PutMapping("/admin/user/ban/{id}")
    public String banUser(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @PutMapping(path = "/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "redirect:/admin/user/{user}";
    }
}
