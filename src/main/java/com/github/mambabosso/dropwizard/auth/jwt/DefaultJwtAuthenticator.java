package com.github.mambabosso.dropwizard.auth.jwt;

import java.io.Serializable;
import java.security.Principal;

public final class DefaultJwtAuthenticator<T extends Principal & Serializable> extends JwtAuthenticator<T> {

    private final JwtHandler<T> handler;

    private DefaultJwtAuthenticator(final JwtHandler<T> handler) {
        super(handler);
        this.handler = handler;
    }

    public JwtHandler<T> getJwtHandler() {
        return handler;
    }

    public static <T extends Principal & Serializable> DefaultJwtAuthenticator<T> create(final JwtConfiguration configuration, final Class<T> tClass) {
        DefaultJwtHandler<T> handler = new DefaultJwtHandler<>(configuration, new DefaultConverter<>(tClass));
        return new DefaultJwtAuthenticator<T>(handler);
    }

}
