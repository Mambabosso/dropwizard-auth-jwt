package example;

import com.github.mambabosso.dropwizard.auth.jwt.JwtHandler;
import io.dropwizard.auth.Auth;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/jwt")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JwtResource {

    private final JwtHandler<User> handler;

    public JwtResource(JwtHandler<User> handler) {
        this.handler = handler;
    }

    @GET
    @Path("/token")
    public Response token(@QueryParam("name") String name, @QueryParam("xyz") String xyz) {
        Optional<String> token = handler.encode(new User(name, xyz));
        if (token.isPresent()) {
            return Response.ok(token.get()).build();
        }
        return Response.status(400).build();
    }

    @GET
    @Path("/get")
    public Response get(@Auth User user) {
        return Response.ok("Hello " + user.getName()).build();
    }

    @GET
    @Path("/validate")
    public Response validate(@QueryParam("token") String token) {
        Optional<User> user = handler.decode(token);
        if (user.isPresent()) {
            return Response.ok("Hello " + user.get().getName()).build();
        }
        return Response.status(401).entity("Invalid token...").build();
    }

}
