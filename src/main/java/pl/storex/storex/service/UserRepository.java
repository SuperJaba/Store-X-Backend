package pl.storex.storex.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.storex.storex.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
