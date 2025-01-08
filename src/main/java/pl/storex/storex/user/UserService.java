package pl.storex.storex.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.storex.storex.model.LoginDTO;
import pl.storex.storex.model.UsersGroup;
import pl.storex.storex.service.UsersGroupRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UsersGroupRepository groupRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(UserDTO dto) {
        User user = User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .role(Role.USER)
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        if (dto.getGroupUUID() != null) {
            user.setGroup_uuid(getGroupIdOrCreateNew(dto.getGroupUUID(), dto.getGroupName(), dto.getName()));
        } else {
            UsersGroup group = groupRepository.save(UsersGroup.builder()
                    .name(dto.getName())
                    .groupOwnerEmail(user.getEmail())
                    .build()
            );
            user.setGroup_uuid(group.getId().toString());
        }
        return userRepository.save(user);
    }

    public User update(UserDTO newUser, String id) {
        return userRepository.findById(UUID.fromString(id))
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    user.setGroup_uuid(newUser.getGroupUUID());
                    return userRepository.save(user);
                })
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .name(newUser.getName())
                                .password(newUser.getPassword())
                                .email(newUser.getPassword())
                                .group_uuid(getGroupIdOrCreateNew(
                                        newUser.getEmail(),
                                        (newUser.getGroupName() != null) ? newUser.getGroupName() : null,
                                        newUser.getEmail()))
                                .build()));
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(UUID.fromString(id));
    }

    public void deleteById(String id) {
        userRepository.deleteById(UUID.fromString(id));
    }

    public boolean checkIfUserExists(String email, String name) {
        return userRepository.existsUserByEmailAndName(email, name);
    }

    private String getGroupIdOrCreateNew(String groupId, String groupName, String ownerEmail) {
        UsersGroup usersGroupById = groupRepository.findUsersGroupById(UUID.fromString(groupId));
        String userGroupId;
        if (usersGroupById != null) {
            userGroupId = usersGroupById.getId().toString();
        } else {
            UsersGroup newUserGroup = groupRepository.save(
                    UsersGroup.builder()
                            .name(groupName)
                            .groupOwnerEmail(ownerEmail)
                            .build()
            );
            userGroupId = newUserGroup.getId().toString();
        }
        return userGroupId;
    }

    public User findByNameAndCheckPass(LoginDTO loginDto) {
        Optional<User> userByName = userRepository.findUserByEmail(loginDto.getEmail());
        if (userByName.isPresent()) {
            if (passwordEncoder.matches(loginDto.getPassword(), userByName.get().getPassword())) {
                return userByName.get();
            }
        }
        return null;
    }

    public ArrayList<User> usersInGroup(String ownerEmail) {
        Optional<UsersGroup> usersGroup = groupRepository.findByGroupOwnerEmail(ownerEmail);
        Optional<ArrayList<User>> users = Optional.empty();
        if (usersGroup.isPresent()) {
            users = userRepository.findUsersByGroupId(usersGroup.get().getId().toString());
        }
        return users.orElseThrow(() -> new UserNotFoundException("No Users to return"));
    }

}
