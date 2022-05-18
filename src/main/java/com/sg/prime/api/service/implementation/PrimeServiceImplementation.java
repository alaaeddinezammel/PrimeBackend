package com.sg.prime.api.service.implementation;

import com.sg.prime.api.domain.Prime;
import com.sg.prime.api.repository.PrimeRepository;
import com.sg.prime.api.service.PrimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.springframework.data.domain.PageRequest.of;


@RequiredArgsConstructor
@Service
@Slf4j
public class PrimeServiceImplementation implements PrimeService {

    private final PrimeRepository primeRepository;

    @Override
    public List<Integer> create(Prime prime) {
        log.info("Saving new number: {}", prime.getInputNumber());
        List<Integer> primes = computePrimesNumber(prime.getInputNumber());

        OptionalDouble averageOfPrimes = primes.stream().mapToInt(Integer::intValue).average();
        prime.setSumOfPrimes(primes.stream().mapToInt(Integer::intValue).sum());
        if (averageOfPrimes.isPresent())
            prime.setAverageOfPrimes(averageOfPrimes.getAsDouble());
        primeRepository.save(prime);

        return primes;
    }


    @Override
    public Collection<Prime> findPrimeSearchesByLimit(Integer limit) {
        log.info("Fetching last 10 primes searched");
        return primeRepository.findPrimesSearchesByLimit(of(0, limit));
    }

    private List<Integer> getPrimesBeforeInputNumber(Integer inputNumber) {
        return IntStream.range(inputNumber, Integer.MAX_VALUE)
                .filter(x -> x > inputNumber && isPrime(x))
                .limit(3)
                .boxed()
                .collect(Collectors.toList());
    }

    private List<Integer> getPrimesAfterInputNumber(Integer inputNumber) {
        return IntStream.range(0, inputNumber)
                .boxed()
                .sorted(Collections.reverseOrder())
                .filter(x -> x < inputNumber && isPrime(x))
                .limit(2)
                .collect(Collectors.toList());
    }

    private List<Integer> computePrimesNumber(Integer inputNumber) {
        return Stream.of(getPrimesBeforeInputNumber(inputNumber), getPrimesAfterInputNumber(inputNumber))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


    private boolean isPrime(Integer number) {
        if (number <= 2)
            return number == 2;
        else
            return (number % 2) != 0
                    &&
                    IntStream.rangeClosed(3, (int) Math.sqrt(number))
                            .filter(n -> n % 2 != 0)
                            .noneMatch(n -> (number % n == 0));
    }
}

