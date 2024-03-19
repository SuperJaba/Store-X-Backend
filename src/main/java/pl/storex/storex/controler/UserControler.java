package pl.storex.storex.controler;

import org.springframework.web.bind.annotation.*;
import pl.storex.storex.model.User;
import pl.storex.storex.model.UserDTO;
import pl.storex.storex.service.UserRepository;
import pl.storex.storex.exceptions.UserNotFoundException;

import java.util.List;
import java.util.UUID;

@RestController
public class UserControler {

    private final UserRepository repository;

    public UserControler(UserRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/users")
    User newUser(@RequestBody UserDTO userDTO) {
        return repository.save(User.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .build());
    }

    // Single user
    @GetMapping("/users/{id}")
    User user(@PathVariable String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody UserDTO newUser, @PathVariable String id) {

        return repository.findById(UUID.fromString(id))
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    return repository.save(User.builder()
                            .name(newUser.getName())
                            .password(newUser.getPassword())
                            .email(newUser.getPassword())
                            .build());
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable String id) {
        repository.deleteById(UUID.fromString(id));
    }

}
