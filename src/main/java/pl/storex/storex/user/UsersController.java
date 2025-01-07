package pl.storex.storex.user;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.storex.storex.model.LoginDTO;
import pl.storex.storex.model.RequestAuth;
import pl.storex.storex.security.JwtService;
import pl.storex.storex.service.UserGroupService;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Store-X User Controller")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService repository;
    private final JwtService jwtService;
    private final UserGroupService userGroupService;

    @GetMapping("/getAll")
    List<User> allUsers() {
        return repository.findAll();
    }

    @PostMapping(value = "/addUser", produces = "application/json", consumes = "application/json")
    ResponseEntity<UserDTO> newUser(@RequestBody UserDTO userDTO) {
        User newUser = repository.save(userDTO);
        return ResponseEntity.ok(UserDTO.builder()
                        .email(newUser.getEmail())
                        .name(newUser.getName())
                        .groupUUID(newUser.getGroup_uuid())
                        .password(newUser.getPassword())
                        .groupName(userGroupService.getGroupByUUID(newUser.getGroup_uuid()).getName())
                .build());
    }

    // Single user
    @GetMapping("/getUserByID/{id}")
    User user(@PathVariable String id) {
        //TODO check if user don't exist or throw error
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping(value = "/groupUsers")
    ResponseEntity<ArrayList<User>> getUsersInGroup(@RequestBody String groupOwnerEmail) {
        return ResponseEntity.ok(userGroupService.usersInGroup(groupOwnerEmail));
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
//    @CrossOrigin(origins = "localhost:52114")
    ResponseEntity<RequestAuth> login(@RequestBody LoginDTO loginDto) {

        if (loginDto.getEmail() != null && loginDto.getPassword() != null) {
            //find user and check pass
            User user = repository.findByNameAndCheckPass(loginDto);
            if (user != null) {
                return ResponseEntity
                        .ok()
                        .body(RequestAuth.builder()
                                .token(jwtService.generateToken(user))
                                .refreshToken(jwtService.generateRefreshToken(user))
                                .build());
            }
        }

        return ResponseEntity.notFound().build();
    }
}
