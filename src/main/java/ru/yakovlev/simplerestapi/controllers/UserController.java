package ru.yakovlev.simplerestapi.controllers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yakovlev.simplerestapi.models.Card;
import ru.yakovlev.simplerestapi.models.User;
import ru.yakovlev.simplerestapi.services.CardService;
import ru.yakovlev.simplerestapi.services.UserService;

import java.math.BigDecimal;

/**
 * Created by alexi on 28.06.2025
 */
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CardService cardService;

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("cards", cardService.getCardsByOwner(user));
        return "user-info";
    }

    @PutMapping(path = "/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("cards", cardService.getCardsByOwner(user));
        return "user-edit";
    }

    @GetMapping("/user/{card}")
    public String cardInfo(@PathVariable("card") Card card, Model model) {
        model.addAttribute("card", card);
        return "card-info";
    }

    @Transactional
    @GetMapping("/user/transfer")
    public String transfer(Long sender, Long receiver, BigDecimal amount) {
        cardService.transfer(sender, receiver, amount);
        return "redirect:/user/{user}";
    }

    @Transactional
    @GetMapping("/user/deposit")
    public String deposit(Long cardId, BigDecimal amount) {
        cardService.deposit(cardId, amount);
        return "redirect:/user/{user}";
    }

    @Transactional
    @GetMapping("/user/withdraw")
    public String withdraw(Long cardId, BigDecimal amount) {
        cardService.withdraw(cardId, amount);
        return "redirect:/user/{user}";
    }
}
