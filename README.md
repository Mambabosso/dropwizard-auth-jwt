# dropwizard-auth-jwt

## Getting started
### Configuration
```java
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
```
### Application (FasterXML/jackson example)
In this example the class 'User' is our principal
```java
DefaultJwtHandler<User> handler = new DefaultJwtHandler<>(configuration.getJwtConfiguration(), 
        new Converter<User, Map<String, Object>>() {

    @Override
    public Map<String, Object> to(User user) {
        // Somehow convert the user into a map -> here with jackson
        // The map will be stored in the token
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(user, new TypeReference<Map<String, Object>>() {});
    }

    @Override
    public User from(Map<String, Object> map) {
        // Somehow convert the map into a user -> here with jackson
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(map, User.class);
    }

});

OAuthCredentialAuthFilter.Builder<User> oauthBuilder = new OAuthCredentialAuthFilter.Builder<>();
oauthBuilder.setAuthenticator(new JwtAuthenticator<>(defaultJWTHandler));
oauthBuilder.setPrefix("Bearer");
environment.jersey().register(new AuthDynamicFeature(oauthBuilder.buildAuthFilter()));
environment.jersey().register(RolesAllowedDynamicFeature.class);
environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
```
### Tokens
#### Encode
```java
Optional<String> token = handler.encode(userObject);
// do something with the token
```
#### Decode
```java
Optional<User> user = handler.decode(token);
// do something with the user
```
## Credits
*   Dropwizard - [GitHub](https://github.com/dropwizard/dropwizard)
*   Auth0 Java Jwt - [GitHub](https://github.com/auth0/java-jwt)
*   Lombok - [GitHub](https://github.com/rzwitserloot/lombok)
