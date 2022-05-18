package com.sg.prime.api.repository;

import com.sg.prime.api.domain.Prime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrimeRepository extends JpaRepository<Prime, Long> {
    @Query("SELECT u FROM Prime u  order by u.creationDate DESC ")
    List<Prime> findPrimesSearchesByLimit(Pageable pageable);
}
