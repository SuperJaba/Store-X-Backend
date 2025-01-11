package pl.storex.storex.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class LoginDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;

    public LoginDTO(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
