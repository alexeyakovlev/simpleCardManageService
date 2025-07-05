package ru.yakovlev.simplerestapi.controllers;

import org.springframework.web.bind.annotation.*;
import ru.yakovlev.simplerestapi.models.Card;
import ru.yakovlev.simplerestapi.models.Status;
import ru.yakovlev.simplerestapi.models.User;
import ru.yakovlev.simplerestapi.services.CardService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by alexi on 30.06.2025
 */
@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardService cardService;

    public CardController(final CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(path = "/{id}")
    public Card getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

//    @GetMapping
//    public List<Card> getUserCards(User owner) {
//        return cardService.getCardsByOwner(owner);
//    }
//
//    @GetMapping
//    public Card getCardByNumber(String number) {
//        return cardService.getCardByNumber(number);
//    }

    @PostMapping
    public String createCard(@RequestBody Card card) {
        return cardService.createCard(card);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCard(@RequestBody Card card) {
        cardService.deleteCardById(card.getId());
    }

    @PutMapping(path = {"id"})
    public void updateCard(@PathVariable Long id,
                           @RequestParam Status status,
                           @RequestParam LocalDate periodOfValidity,
                           @RequestParam BigDecimal balance) {
        cardService.updateCardById(id, status, periodOfValidity, balance);
    }
}
