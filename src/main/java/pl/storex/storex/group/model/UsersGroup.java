package pl.storex.storex.group.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "users_groups")
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
    private Date created_at;
    private Date updated_at;
    private Long updated_by;

}
