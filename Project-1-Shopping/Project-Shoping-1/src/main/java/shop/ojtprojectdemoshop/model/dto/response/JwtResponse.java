package shop.ojtprojectdemoshop.model.dto.response;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {
    private final String type = "Bearer";
    private String accessToken;
    private String fullName;
    private String email;
    private boolean status;
    private Collection<? extends GrantedAuthority> roles;

}
