package pl.storex.storex.group.model;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UsersGroupDTO implements Serializable {

    private Long groupId;
    private String name;
    private String group_owner_email;

}
