package pl.storex.storex.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsUserByEmailAndName(String email, String name);

    User findUserByName(String name);

    @Query(value = "SELECT u from appuser u where u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Query("select u from appuser u where u.group_uuid = :groupId ")
    Optional<ArrayList<User>> findUsersByGroup_uuid(@Param("groupId") UUID id);

}
