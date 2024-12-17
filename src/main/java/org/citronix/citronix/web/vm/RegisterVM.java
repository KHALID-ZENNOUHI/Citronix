package org.citronix.citronix.web.vm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterVM {
    private Long id;

    @NotBlank
    @Size(min = 4, max = 200)
    private String username;

    @NotBlank
    @Size(min = 4, max = 200)
    private String firstName;

    @NotBlank
    @Size(min = 4, max = 200)
    private String lastName;

    @NotBlank
    @Size(min = 4, max = 200)
    private String email;

    @NotBlank
    private String role;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
}
