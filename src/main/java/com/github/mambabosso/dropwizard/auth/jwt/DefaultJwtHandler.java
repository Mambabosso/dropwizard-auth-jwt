package com.github.mambabosso.dropwizard.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import lombok.Getter;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DefaultJwtHandler<T extends Principal & Serializable> implements JwtHandler<T> {

    @Getter
    private final JwtConfiguration configuration;

    @Getter
    private final Converter<T, Map<String, Object>> converter;

    public DefaultJwtHandler(final JwtConfiguration configuration, final Converter<T, Map<String, Object>> converter) {
        this.configuration = Objects.requireNonNull(configuration);
        this.converter = Objects.requireNonNull(converter);
    }

    protected String encodePrincipal(T principal) {

        Objects.requireNonNull(principal);

        JWTCreator.Builder builder = JWT.create();

        builder.withIssuer(configuration.getIssuer());
        builder.withSubject(configuration.getSubject());
        builder.withAudience(configuration.getAudience());
        builder.withExpiresAt(configuration.expiresAt());
        builder.withNotBefore(configuration.getNotBefore());
        builder.withIssuedAt(configuration.issuedAt());
        builder.withJWTId(configuration.getJwtID());

        builder.withClaim(configuration.getPrincipalClaim(), converter.to(principal));

        return builder.sign(algorithm());

    }

    @Override
    public Optional<String> encode(T principal) {
        try {
            return Optional.of(encodePrincipal(principal));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    protected T decodeToken(String token) {

        Objects.requireNonNull(token);

        Verification verification = JWT.require(algorithm());

        verification.withIssuer(configuration.getIssuer());
        verification.withSubject(configuration.getSubject());
        verification.withAudience(configuration.getAudience());
        verification.withJWTId(configuration.getJwtID());

        DecodedJWT jwt = verification.build().verify(token);

        return converter.from(jwt.getClaim(configuration.getPrincipalClaim()).asMap());

    }

    @Override
    public Optional<T> decode(String token) {
        try {
            return Optional.of(decodeToken(token));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    protected Algorithm algorithm() {
        return Algorithm.HMAC256(configuration.getSecret());
    }

}
