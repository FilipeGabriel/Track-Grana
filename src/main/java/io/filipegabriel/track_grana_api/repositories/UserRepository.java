package io.filipegabriel.track_grana_api.repositories;

import io.filipegabriel.track_grana_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
