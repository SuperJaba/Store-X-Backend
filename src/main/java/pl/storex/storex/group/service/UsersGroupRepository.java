package pl.storex.storex.group.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.storex.storex.group.model.UsersGroup;

import java.util.Optional;

public interface UsersGroupRepository extends JpaRepository<UsersGroup, Long> {
//    @Query(value = "select g from group_of_users g where g.name = ?1")
    UsersGroup findByName(String groupName);
    UsersGroup findUsersGroupById(Long id);

    Optional<UsersGroup> findByGroupOwnerEmail(String groupOwnerEmail);

    Optional<UsersGroup> findById(Long id);
}
