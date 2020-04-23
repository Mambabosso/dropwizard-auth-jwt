package com.github.mambabosso.dropwizard.auth.jwt;

import java.io.Serializable;
import java.security.Principal;

/**
 * Handle JWT tokens
 * @param <T> Principal type
 */
public interface JwtHandler<T extends Principal & Serializable> extends JwtEncoder<T>, JwtDecoder<T> {
}
