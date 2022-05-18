package com.sg.prime.api.service.implementation;

import com.sg.prime.api.domain.Prime;
import com.sg.prime.api.repository.PrimeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PrimeServiceImplementation.class})
@ExtendWith(SpringExtension.class)
class PrimeServiceImplementationTest {
    @MockBean
    private PrimeRepository primeRepository;

    @Autowired
    private PrimeServiceImplementation primeServiceImplementation;

    @Test
    void should_create_prime() {
        Prime prime = new Prime();
        prime.setAverageOfPrimes(10.0d);
        prime.setCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        prime.setId(123L);
        prime.setInputNumber(10);
        prime.setSumOfPrimes(1);
        when(this.primeRepository.save(any())).thenReturn(prime);
        Prime expectedPrime = new Prime();
        expectedPrime.setAverageOfPrimes(10.0d);
        expectedPrime.setCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        expectedPrime.setId(123L);
        expectedPrime.setInputNumber(10);
        expectedPrime.setSumOfPrimes(1);
        List<Integer> actualCreateResult = this.primeServiceImplementation.create(expectedPrime);
        assertEquals(5, actualCreateResult.size());
        verify(this.primeRepository).save(any());
        assertEquals(10.6d, expectedPrime.getAverageOfPrimes());
        assertEquals(53, expectedPrime.getSumOfPrimes().intValue());
    }


    @Test
    void should_find_prime_searches_by_limit() {
        var primeList = new ArrayList<Prime>();
        when(this.primeRepository.findPrimesSearchesByLimit(any()))
                .thenReturn(primeList);
        Collection<Prime> actualFindPrimeSearchesByLimitResult = this.primeServiceImplementation
                .findPrimeSearchesByLimit(1);
        assertSame(primeList, actualFindPrimeSearchesByLimitResult);
        assertTrue(actualFindPrimeSearchesByLimitResult.isEmpty());
        verify(this.primeRepository).findPrimesSearchesByLimit(any());
    }


}

