package ru.yakovlev.simplerestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.yakovlev.simplerestapi.models.Card;
import ru.yakovlev.simplerestapi.models.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by alexi on 29.06.2025
 */
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query(value = "select * from cards where owner = :owner", nativeQuery = true)
    List<Card> findByOwner(@Param(value = "owner") User owner);

    @Query(value = "select * from cards where number = :number", nativeQuery = true)
    Optional<Card> findByNumber(@Param(value = "number") String number);
}
