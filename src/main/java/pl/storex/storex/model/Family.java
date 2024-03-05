package pl.storex.storex.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Family implements Serializable {

    @Id
    @Column(name = "family_uuid", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

}
