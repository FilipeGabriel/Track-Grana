package io.filipegabriel.track_grana_api.repositories;

import io.filipegabriel.track_grana_api.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Users, Long> {

    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);

}
