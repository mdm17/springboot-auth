package id.mdm17.validation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsernamePassword {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
