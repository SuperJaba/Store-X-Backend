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

@Service
@RequiredArgsConstructor
public class UserGroupService {
    private final UsersGroupRepository groupRepo;
    private final UserRepository user;

    public UsersGroup getGroupById(Long id) {
        Optional<UsersGroup> group = groupRepo.findById(id);
        return group.orElseThrow(() -> new NullPointerException("Group was not found"));
    }


    public UsersGroup updateGroup(UsersGroupDTO usersGroupDTO) {
        Optional<UsersGroup> groupById = groupRepo.findById(usersGroupDTO.getId());
        Optional<User> owner = user.findUserByEmail(groupById.get().getGroupOwnerEmail());
        User userObj;
        userObj = owner.orElse(null);
        return groupRepo.findById(usersGroupDTO.getId()).map(
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

    UsersGroup findGroup(Long groupId) {
        return groupRepo.findById(groupId).orElseThrow(
                ()-> new NonUniqueResultException("Group not found"));
    }

    public void removeGroup(Long groupId) {
        Optional<UsersGroup> group = Optional.ofNullable(findGroup(groupId));
        group.ifPresent(groupRepo::delete);
    }

    public void removeUserFromGroup(Long userId) {
        Optional<User> optionalUser = user.findById(userId);
        if (optionalUser.isPresent()) {
            User user1 = optionalUser.get();
            user.save(user1);
        }
    }

    public Optional<UsersGroup> findGroup(UsersGroupDTO groupDTO) {
        Optional<UsersGroup> group = Optional.empty();
        if (groupDTO.getId() != null) {
            group = groupRepo.findById(groupDTO.getId());
        }
        if (groupDTO.getName() != null) {
            group = Optional.ofNullable(groupRepo.findByName(groupDTO.getName()));
        }
        if (groupDTO.getGroup_owner_email() != null) {
            group = groupRepo.findByGroupOwnerEmail(groupDTO.getGroup_owner_email());
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
