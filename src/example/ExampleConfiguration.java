package example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.mambabosso.dropwizard.auth.jwt.JwtConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ExampleConfiguration extends io.dropwizard.Configuration {

    @Valid
    @NotNull
    private JwtConfiguration jwtConfiguration;

    @JsonProperty("jwt")
    public JwtConfiguration getJwtConfiguration() {
        return jwtConfiguration;
    }

    @JsonProperty("jwt")
    public void setJwtConfiguration(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

}
