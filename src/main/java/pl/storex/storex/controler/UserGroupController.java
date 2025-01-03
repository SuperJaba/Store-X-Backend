package pl.storex.storex.controler;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.storex.storex.model.User;
import pl.storex.storex.service.UserGroupService;

import java.util.ArrayList;

@Tag(name = "Store-X Group Controller")
@RestController
public class UserGroupController {

    private final UserGroupService userGroup;

    public UserGroupController(UserGroupService userGroup) {
        this.userGroup = userGroup;
    }

    public ArrayList<User> getAllUsersInGroup(@RequestBody String body) {

        return null;
    }
}
