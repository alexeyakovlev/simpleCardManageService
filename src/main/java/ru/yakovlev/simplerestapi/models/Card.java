package ru.yakovlev.simplerestapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by alexi on 29.06.2025
 */
@Entity
@Table(name = "cards")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;

    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "period_of_validity")
    private LocalDate periodOfValidity;

    private BigDecimal balance;

    @PrePersist
    private void prePersist() {
        periodOfValidity = LocalDate.now();
    }
}
