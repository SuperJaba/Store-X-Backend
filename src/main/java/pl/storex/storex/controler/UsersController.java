package pl.storex.storex.controler;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.storex.storex.exceptions.UserNotFoundException;
import pl.storex.storex.model.LoginDTO;
import pl.storex.storex.model.User;
import pl.storex.storex.model.UserDTO;
import pl.storex.storex.service.UserService;

import java.util.List;

@Tag(name = "Store-X User Controller")
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService repository;

    public UsersController(UserService repository) {
        this.repository = repository;
    }


    @GetMapping("/getAll")
    List<User> allUsers() {
        return repository.findAll();
    }

    @PostMapping(value = "/addUser", produces = "application/json", consumes = "application/json")
    User newUser(@RequestBody UserDTO userDTO) {
        return repository.save(userDTO);
    }

    // Single user
    @GetMapping("/getUserByID/{id}")
    User user(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @PutMapping(value = "/updateUser/{id}", consumes = "application/json", produces = "application/json")
    User updateUser(@RequestBody UserDTO newUser, @PathVariable String id) {
        return repository.update(newUser, id);
    }

    @DeleteMapping("/removeUser/{id}")
    void deleteUser(@PathVariable String id) {
        repository.deleteById(id);
    }

    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    ResponseEntity<String> login(@RequestBody LoginDTO loginDto) {
        if (loginDto == null) {
            return new ResponseEntity<>("Body can't be null", HttpStatus.BAD_REQUEST);
        }
        if(loginDto.getEmail() != null && loginDto.getPassword() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "token");
            //find user and check pass
            User user = repository.findByNameAndCheckPass(loginDto);
            if (user != null) {
                return new ResponseEntity<>("User: " + user, headers, HttpStatus.FOUND);
            }
        }

        return new ResponseEntity<>("Wrong login or password", HttpStatus.UNAUTHORIZED);
    }
}
