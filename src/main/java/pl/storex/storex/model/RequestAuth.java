package pl.storex.storex.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestAuth {
    private String token;
    private String refreshToken;
}
