package pl.storex.storex.model;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UsersGroupDTO implements Serializable {
    private String groupId;
    private String name;
    private String ownerId;

    
}
