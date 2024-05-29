package shop.ojtprojectdemoshop.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FormRegister {

    @NotBlank(message = "username must not be blank")
    private String username;
    @NotEmpty(message = "FullName must not be null")
    private String fullName;
    @Email(message = "Email invalid format")
    private String email;
    @Size(min = 6 , message = "password min 6 character")
    private String password;
    private Set<String> roles;

}
