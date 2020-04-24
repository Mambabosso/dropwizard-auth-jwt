package example;

import com.github.mambabosso.dropwizard.auth.jwt.DefaultJwtAuthenticator;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Environment;
import io.dropwizard.Application;

public class ExampleApplication extends Application<ExampleConfiguration> {

    public static void main(String[] args) throws Exception {
        new ExampleApplication().run(args);
    }

    public void run(ExampleConfiguration configuration, Environment environment) throws Exception {

        DefaultJwtAuthenticator<User> authenticator = DefaultJwtAuthenticator.create(
                configuration.getJwtConfiguration(), User.class);

        OAuthCredentialAuthFilter.Builder<User> oauthBuilder = new OAuthCredentialAuthFilter.Builder<User>();
        oauthBuilder.setAuthenticator(authenticator);
        oauthBuilder.setPrefix("Bearer");

        environment.jersey().register(new AuthDynamicFeature(oauthBuilder.buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<User>(User.class));

        environment.jersey().register(new JwtResource(authenticator.getJwtHandler()));

    }

}
