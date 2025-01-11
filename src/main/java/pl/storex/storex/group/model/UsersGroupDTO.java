package pl.storex.storex.group.model;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class UsersGroupDTO implements Serializable {

    private Long id;
    private String name;
    private String group_owner_email;
    private Date created_at;
    private Date updated_at;
    private Long updated_by;

}
