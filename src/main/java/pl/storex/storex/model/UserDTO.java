package pl.storex.storex.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {
    private String email;
    private String name;
    private String password;
}
