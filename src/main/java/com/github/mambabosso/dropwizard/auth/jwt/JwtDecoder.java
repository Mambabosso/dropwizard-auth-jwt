package com.github.mambabosso.dropwizard.auth.jwt;

import java.io.Serializable;
import java.security.Principal;
import java.util.Optional;

/**
 * Decode JWT tokens
 * @param <T> Principal type
 */
public interface JwtDecoder<T extends Principal & Serializable> {

    /**
     * Decodes the token into a principal
     * @param token The token
     * @return The decoded principal
     */
    public Optional<T> decode(String token);

}
