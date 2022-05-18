package com.sg.prime.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
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


    private LocalDateTime creationDate = LocalDateTime.now();


    private Integer inputNumber;

    private Integer sumOfPrimes;

    private double averageOfPrimes;



}
