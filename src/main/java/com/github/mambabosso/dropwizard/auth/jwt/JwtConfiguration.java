package com.github.mambabosso.dropwizard.auth.jwt;

import io.dropwizard.util.Duration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.Date;

/**
 * A basic configuration for JWT
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtConfiguration {

    @Valid
    private String secret;

    @Valid
    private String principalClaim = "principal";

    @Valid
    private String issuer;

    @Valid
    private String subject;

    @Valid
    private String[] audience;

    @Valid
    private Date expiresAt;

    @Valid
    private Duration lifetime;

    @Valid
    private Date notBefore;

    @Valid
    private Date issuedAt;

    @Valid
    private String jwtID;

    /**
     * Current date plus lifetime
     * @return Current date plus lifetime or expiresAt if lifetime is not set
     */
    public Date expiresAt() {
        if (lifetime != null) {
            return new Date(issuedAt().getTime() + lifetime.toMilliseconds());
        }
        return expiresAt;
    }

    /**
     * Current date
     * @return Current date or issuedAt if it is set
     */
    public Date issuedAt() {
        if (issuedAt == null) {
            return new Date(System.currentTimeMillis());
        }
        return issuedAt;
    }

}
