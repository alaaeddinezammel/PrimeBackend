package com.sg.prime.api.repository;

import com.sg.prime.api.domain.Prime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimeRepository extends JpaRepository<Prime, Long> {


}
