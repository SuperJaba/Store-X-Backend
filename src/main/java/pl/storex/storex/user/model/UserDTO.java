package pl.storex.storex.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {
    @Schema(example = "test@test.com")
    private String email;
    @Schema(example = "Janusz")
    private String name;
    @Schema(example = "154dfEfjk%ggSD")
    private String password;
    @Schema(description = "Optional", example = "Optional: '7f9d1b2e-5c79-4e24-8ca1-d180c42c6fee'")
    private String groupUUID;
    @Schema(description = "Optional", example = "Optional: test@test.com or my group name")
    private String groupName;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String getGroupId() {
        var group = this.groupUUID;
        if (group == null) {
            group = this.email;
        }
        return group;
    }
}
