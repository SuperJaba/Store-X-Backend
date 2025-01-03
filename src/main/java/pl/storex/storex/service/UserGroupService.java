package pl.storex.storex.service;

import org.springframework.stereotype.Service;
import pl.storex.storex.model.User;
import pl.storex.storex.model.UsersGroup;

import java.util.ArrayList;

@Service
public class UserGroupService {
    private final UsersGroupRepository userGroup;
    private final UserRepository user;

    public UserGroupService(UsersGroupRepository userGroup, UserRepository user) {
        this.userGroup = userGroup;
        this.user = user;
    }

    public ArrayList<User> usersInGroup(String ownerEmail) {
        UsersGroup usersGroupByGroupOwnerEmail = userGroup.findUsersGroupByGroupOwnerEmail(ownerEmail);
        return null;
    }
}
