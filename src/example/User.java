package example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.security.Principal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Principal, Serializable {

    private String name;
    private String xyz;

    @Override
    public String getName() {
        return name;
    }

}
