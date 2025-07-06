package ru.yakovlev.simplerestapi.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovlev.simplerestapi.models.Card;
import ru.yakovlev.simplerestapi.models.Status;
import ru.yakovlev.simplerestapi.models.User;
import ru.yakovlev.simplerestapi.repositories.CardRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by alexi on 29.06.2025
 */
@Service
@Slf4j
public class CardService {
    private CardRepository cardRepository;
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card getCardById(Long id) {
        Optional<Card> card = cardRepository.findById(id);
        if (!card.isPresent()) {
            throw new IllegalArgumentException("Card with id " + id + " not found");
        }
        return card.get();
    }

    public List<Card> getCardsByOwner(User owner) {
        return cardRepository.findByOwner(owner);
    }

    public Card getCardByNumber(Long number) {
        Optional<Card> card = cardRepository.findByNumber(number);
        if (!card.isPresent()) {
            throw new IllegalArgumentException("Card with number " + number + " not found");
        }
        return card.get();
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Transactional
    public void createCard(Card card) {
        cardRepository.save(card);
    }

    @Transactional
    public void deleteCardById(Long id) {
        Optional<Card> card = cardRepository.findById(id);
        if (!card.isPresent()) {
            throw new IllegalArgumentException("Card with id " + id + " not found");
        }
        cardRepository.delete(card.get());
    }

    @Transactional
    public void updatePeriodOfValidity(Long id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent()) {
            throw new IllegalArgumentException("Card with id " + id + " not found");
        }
        Card card = optionalCard.get();
        card.setPeriodOfValidity(LocalDate.now().plusYears(1));
        log.info("Card with id " + id + " updated period of validity");
        cardRepository.save(card);
    }

    @Transactional
    public void transfer(Long idOfSender, Long idOfReceiver, BigDecimal amount) {
        Optional<Card> optionalCardSender = cardRepository.findById(idOfSender);
        if (!optionalCardSender.isPresent()) {
            throw new IllegalArgumentException("Card with id " + idOfSender + " not found");
        }
        Optional<Card> optionalCardReceiver = cardRepository.findById(idOfReceiver);
        if (!optionalCardReceiver.isPresent()) {
            throw new IllegalArgumentException("Card with id " + idOfReceiver + " not found");
        }
        Card cardSender = optionalCardSender.get();
        Card cardReceiver = optionalCardReceiver.get();
        if (cardSender.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Card with id " + idOfSender + " balance is less than current balance");
        }
        cardSender.setBalance(cardSender.getBalance().subtract(amount));
        cardReceiver.setBalance(cardReceiver.getBalance().add(amount));
        log.info("Card with id " + idOfSender + " sent amount " + amount + " to receiver with id " + idOfReceiver);
        cardRepository.save(cardSender);
        cardRepository.save(cardReceiver);
    }

    @Transactional
    public void deposit(Long id, BigDecimal amount) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent()) {
            throw new IllegalArgumentException("Card with id " + id + " not found");
        }
        Card card = optionalCard.get();
        card.setBalance(card.getBalance().add(amount));
        cardRepository.save(card);
        log.info("The id " + id + " card was replenished in the amount of " + amount);
    }

    @Transactional
    public void withdraw(Long id, BigDecimal amount) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent()) {
            throw new IllegalArgumentException("Card with id " + id + " not found");
        }
        Card card = optionalCard.get();
        card.setBalance(card.getBalance().subtract(amount));
        cardRepository.save(card);
        log.info("The id " + id + " card was withdrawn in the amount of " + amount);
    }

    public void changeCardStatus(Long number, Status status) {
        Optional<Card> optionalCard = cardRepository.findById(number);
        if (!optionalCard.isPresent()) {
            throw new IllegalArgumentException("Card with number " + number + " not found");
        }
        Card card = optionalCard.get();
        card.setStatus(status);
        cardRepository.save(card);
        log.info("The id " + number + " card was changed to " + status);
    }
}
