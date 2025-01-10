package pl.storex.storex.group.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "group_of_users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersGroup implements Serializable {

    @Id
    @Column(name = "user_group_uuid", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String groupOwnerEmail;

}
