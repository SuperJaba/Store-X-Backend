package pl.storex.storex.controler;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.storex.storex.exceptions.UserNotFoundException;
import pl.storex.storex.model.User;
import pl.storex.storex.model.UserDTO;
import pl.storex.storex.service.UserRepository;

import java.util.List;
import java.util.UUID;

@Tag(name = "Store-X User Controller")
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserRepository repository;

    public UsersController(UserRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/getAll")
    List<User> allUsers() {
        return repository.findAll();
    }

    @PostMapping("/addUser")
    User newUser(@RequestBody UserDTO userDTO) {
        return repository.save(User.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .group_uuid(userDTO.getGroupId())
                .build());
    }

    // Single user
    @GetMapping("/getUserByID/{id}")
    User user(@PathVariable String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/updateUser/{id}")
    User updateUser(@RequestBody UserDTO newUser, @PathVariable String id) {

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

    @DeleteMapping("/removeUser/{id}")
    void deleteUser(@PathVariable String id) {
        repository.deleteById(UUID.fromString(id));
    }

}
