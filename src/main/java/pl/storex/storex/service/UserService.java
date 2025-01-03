package pl.storex.storex.service;

import org.springframework.stereotype.Service;
import pl.storex.storex.model.LoginDTO;
import pl.storex.storex.model.User;
import pl.storex.storex.model.UserDTO;
import pl.storex.storex.model.UsersGroup;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UsersGroupRepository groupRepository;

    public UserService(UserRepository userRepository, UsersGroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(UserDTO dto) {
        User user = User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
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
                                        (newUser.getGroupName()!= null)? newUser.getGroupName(): null,
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
        User userByName = userRepository.findUserByEmail(loginDto.getEmail());
        if (userByName != null && Objects.equals(loginDto.getPassword(), userByName.getPassword())) {
            return userByName;
        }
        return null;
    }
}
