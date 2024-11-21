package io.filipegabriel.track_grana_api.resources.records;

import io.filipegabriel.track_grana_api.resources.dto.UserDTO;

public record AuthenticationDTO(String email, String password) {
}
