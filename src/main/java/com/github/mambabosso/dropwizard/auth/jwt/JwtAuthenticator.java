package com.github.mambabosso.dropwizard.auth.jwt;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.io.Serializable;
import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

public class JwtAuthenticator<T extends Principal & Serializable> implements Authenticator<String, T> {

    private final JwtDecoder<T> decoder;

    public JwtAuthenticator(final JwtDecoder<T> decoder) {
        this.decoder = Objects.requireNonNull(decoder);
    }

    @Override
    public Optional<T> authenticate(String token) throws AuthenticationException {
        return decoder.decode(token);
    }

    public JwtDecoder<T> getJwtDecoder() {
        return decoder;
    }

}
