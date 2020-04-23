package com.github.mambabosso.dropwizard.auth.jwt;

import java.io.Serializable;
import java.security.Principal;
import java.util.Optional;

/**
 * Encode JWT tokens
 * @param <T> Principal type
 */
public interface JwtEncoder<T extends Principal & Serializable> {

    /**
     * Encodes the principal into a JWT token
     * @param principal The principal
     * @return The generated JWT token
     */
    public Optional<String> encode(T principal);

}
