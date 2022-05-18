package com.sg.prime.api.service.implimentation;
import com.sg.prime.api.domain.Prime;
import com.sg.prime.api.repository.PrimeRepository;
import com.sg.prime.api.service.PrimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


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
    public Collection<Prime> findPrimesSearchByLimit(Integer limit) {
        log.info("Fetching last 10 primes searched");
        return primeRepository.findAll();
    }


    private List<Integer> computePrimesNumber(Integer inputNumber) {
        List<Integer> primesBelong = IntStream.range(inputNumber, Integer.MAX_VALUE)
                .filter(x -> x > inputNumber && isPrime(x))
                .limit(3)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> primesBelow = IntStream.range(0, inputNumber)
                .boxed()
                .filter(x -> x < inputNumber && isPrime(x))
                .limit(2)
                .collect(Collectors.toList());

        return Stream.of(primesBelong, primesBelow)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


    private boolean isPrime(Integer number) {
                    return true;
    }
}

