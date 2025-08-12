package io.filipegabriel.track_grana_api.repositories;

import io.filipegabriel.track_grana_api.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByUserName(String userName);
}
