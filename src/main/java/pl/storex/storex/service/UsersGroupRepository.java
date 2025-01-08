package pl.storex.storex.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.storex.storex.model.UsersGroup;

import java.util.Optional;
import java.util.UUID;

public interface UsersGroupRepository extends JpaRepository<UsersGroup, UUID> {
//    @Query(value = "select g from group_of_users g where g.name = ?1")
    UsersGroup findByName(String groupName);
    UsersGroup findUsersGroupById(UUID uuid);

    Optional<UsersGroup> findByGroupOwnerEmail(String groupOwnerEmail);

    Optional<UsersGroup> findById(UUID id);
}
