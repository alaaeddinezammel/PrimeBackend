package com.sg.prime.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRIMES")
public class Prime {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(nullable = false)
    private Long id;

    @CreatedDate
    private LocalDateTime creationDate = LocalDateTime.now();

    @NotNull
    private Integer inputNumber;

    private Integer sumOfPrimes;

    private double averageOfPrimes;


}
