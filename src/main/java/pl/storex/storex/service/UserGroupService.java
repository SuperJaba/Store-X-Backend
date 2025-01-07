package pl.storex.storex.service;

import jakarta.persistence.NonUniqueResultException;
import org.springframework.stereotype.Service;
import pl.storex.storex.model.UsersGroupDTO;
import pl.storex.storex.user.User;
import pl.storex.storex.model.UsersGroup;
import pl.storex.storex.user.UserNotFoundException;
import pl.storex.storex.user.UserRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserGroupService {
    private final UsersGroupRepository groupRepo;
    private final UserRepository user;

    public UserGroupService(UsersGroupRepository userGroup, UserRepository user) {
        this.groupRepo = userGroup;
        this.user = user;
    }

    public ArrayList<User> usersInGroup(String ownerEmail) {
        UsersGroup usersGroupByGroupOwnerEmail = groupRepo.findUsersGroupByGroupOwnerEmail(ownerEmail);
        Optional<ArrayList<User>> users = user.findUsersByGroup_uuid(usersGroupByGroupOwnerEmail.getId());
        return users.orElseThrow(() -> new UserNotFoundException("No Users to return"));
    }

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

    public void removeUserFromGroup(String groupId, String userId) {

    }
}
