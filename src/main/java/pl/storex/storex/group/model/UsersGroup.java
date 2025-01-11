package pl.storex.storex.group.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "group_of_users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersGroup implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "group_owner_email")
    private String groupOwnerEmail;

}
