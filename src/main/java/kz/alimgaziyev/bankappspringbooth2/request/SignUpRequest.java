package kz.alimgaziyev.bankappspringbooth2.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
public class SignUpRequest {
    @NotBlank @Email
    private String email;
    @NotBlank
    private String password;
    private Set<String> roles;
}
