package com.github.mambabosso.dropwizard.auth.jwt;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.io.Serializable;
import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

public class JwtAuthenticator<T extends Principal & Serializable> implements Authenticator<String, T> {

    private final JwtHandler<T> handler;

    public JwtAuthenticator(final JwtHandler<T> handler) {
        this.handler = Objects.requireNonNull(handler);
    }

    @Override
    public Optional<T> authenticate(String token) throws AuthenticationException {
        return handler.decode(token);
    }

    public JwtHandler<T> getJwtHandler() {
        return handler;
    }

}
