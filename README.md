# dropwizard-auth-jwt
## Maven
```xml
<dependency>
    <groupId>com.github.mambabosso</groupId>
    <artifactId>dropwizard-auth-jwt</artifactId>
    <version>1.1</version>
</dependency>
```
## Getting started
### Example
Example Code - [Link](https://github.com/Mambabosso/dropwizard-auth-jwt/tree/master/src/example)
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
### Application
In this example the class 'User' is our principal
```java
authenticator = DefaultJwtAuthenticator.create(configuration.getJwtConfiguration(), User.class);

OAuthCredentialAuthFilter.Builder<User> oauthBuilder = new OAuthCredentialAuthFilter.Builder<>();
oauthBuilder.setAuthenticator(authenticator);
oauthBuilder.setPrefix("Bearer");

environment.jersey().register(new AuthDynamicFeature(oauthBuilder.buildAuthFilter()));
```
### Tokens
#### Encode
```java
Optional<String> token = authenticator.getJwtHandler().encode(userObject);
// do something with the token
```
#### Decode
```java
Optional<User> user = authenticator.getJwtHandler().decode(token);
// do something with the user
```
## Credits
*   Dropwizard - [GitHub](https://github.com/dropwizard/dropwizard)
*   FasterXML Jackson - [GitHub](https://github.com/FasterXML/jackson)
*   Auth0 Java Jwt - [GitHub](https://github.com/auth0/java-jwt)
*   Lombok - [GitHub](https://github.com/rzwitserloot/lombok)
