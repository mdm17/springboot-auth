package id.mdm17.validation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterReq {
    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
