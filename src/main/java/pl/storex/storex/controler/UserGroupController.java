package pl.storex.storex.controler;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.storex.storex.model.UsersGroup;
import pl.storex.storex.model.UsersGroupDTO;
import pl.storex.storex.user.User;
import pl.storex.storex.service.UserGroupService;

import java.util.ArrayList;

@Tag(name = "Store-X Group Controller")
@RestController("group")
@RequiredArgsConstructor
public class UserGroupController {

    private final UserGroupService userGroupService;

    public ArrayList<User> getAllUsersInGroup(@RequestBody String body) {
        return null;
    }

    @PostMapping(value = "/update", consumes = "application/json")
    ResponseEntity<UsersGroup> updateGroupName(@RequestBody UsersGroupDTO usersGroupDto) {
        return ResponseEntity.ok(userGroupService.updateGroup(usersGroupDto));
    }

    @DeleteMapping("/removeGroup")
    ResponseEntity.BodyBuilder removeGroup(@RequestBody String groupId) {
        userGroupService.removeGroup(groupId);
        return ResponseEntity.ok();
    }

}
