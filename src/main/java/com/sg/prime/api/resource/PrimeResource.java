package com.sg.prime.api.resource;

import com.sg.prime.api.domain.Prime;
import com.sg.prime.api.domain.Response;
import com.sg.prime.api.service.PrimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/primes")
@RequiredArgsConstructor
public class PrimeResource {

    private final PrimeService primeService;


    @GetMapping
    public ResponseEntity<Response> getPrimesFromInputNumber(@RequestParam Integer limit) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("primes", primeService.findPrimeSearchesByLimit(limit)))
                        .message("Primes retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addInputNumber(@RequestBody @Valid Prime prime) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("prime", primeService.create(prime)))
                        .message("Prime created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }


}
