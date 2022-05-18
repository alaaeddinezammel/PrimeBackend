package com.sg.prime.api.service;

import com.sg.prime.api.domain.Prime;

import java.util.Collection;
import java.util.List;

public interface PrimeService {
    List<Integer> create(Prime prime);

    Collection<Prime> findPrimeSearchesByLimit(Integer limit);
}
