package pl.storex.storex.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.storex.storex.model.User;

import java.util.ArrayList;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsUserByEmailAndName(String email, String name);

    User findUserByName(String name);

    User findUserByEmail(String email);

    ArrayList<User> findUsersByGroup_uuid(UUID groupId);
}
