package pl.storex.storex.group.service;

import jakarta.persistence.NonUniqueResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.storex.storex.group.model.UsersGroupDTO;
import pl.storex.storex.user.model.User;
import pl.storex.storex.group.model.UsersGroup;
import pl.storex.storex.user.model.UserDTO;
import pl.storex.storex.user.service.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserGroupService {
    private final UsersGroupRepository groupRepo;
    private final UserRepository user;

    public UsersGroup getGroupByUUID(String uuid) {
        Optional<UsersGroup> group = groupRepo.findById(UUID.fromString(uuid));
        return group.orElseThrow(() -> new NullPointerException("Group was not found"));
    }


    public UsersGroup updateGroup(UsersGroupDTO usersGroupDTO) {
        Optional<User> owner = user.findById(UUID.fromString(usersGroupDTO.getOwnerId()));
        User userObj;
        userObj = owner.orElse(null);
        return groupRepo.findById(UUID.fromString(usersGroupDTO.getGroupId())).map(
                group -> {
                    group.setName(usersGroupDTO.getName());
                    assert userObj != null;
                    group.setGroupOwnerEmail(userObj.getEmail());
                    return groupRepo.save(group);
                }).orElseGet(() -> {
                    assert userObj != null;
                    return groupRepo.save(
                            UsersGroup.builder()
                                    .name(usersGroupDTO.getName())
                                    .groupOwnerEmail(userObj.getEmail())
                                    .build());
                }
        );
    }

    UsersGroup findGroup(String groupId) {
        return groupRepo.findById(UUID.fromString(groupId)).orElseThrow(
                ()-> new NonUniqueResultException("Group not found"));
    }

    public void removeGroup(String groupId) {
        Optional<UsersGroup> group = Optional.ofNullable(findGroup(groupId));
        group.ifPresent(groupRepo::delete);
    }

    public void removeUserFromGroup(String userId) {
        Optional<User> optionalUser = user.findById(UUID.fromString(userId));
        if (optionalUser.isPresent()) {
            User user1 = optionalUser.get();
            user.save(user1);
        }
    }

    public Optional<UsersGroup> findGroup(UsersGroupDTO groupDTO) {
        Optional<UsersGroup> group = Optional.empty();
        if (groupDTO.getGroupId() != null) {
            group = groupRepo.findById(UUID.fromString(groupDTO.getGroupId()));
        }
        if (groupDTO.getName() != null) {
            group = Optional.ofNullable(groupRepo.findByName(groupDTO.getName()));
        }
        if (groupDTO.getOwnerId() != null) {
            group = groupRepo.findByGroupOwnerEmail(groupDTO.getOwnerId());
        }
        return group;
    }

     public UserDTO findUsersWithoutGroup(UserDTO userDTO) {
         Optional<User> optionalUser = user.findUserByEmail(userDTO.getEmail());
         UserDTO dto = null;
         if (optionalUser.isPresent()) {
             dto = User.toDTO(optionalUser.get());
         }
         return dto;
    }
}
