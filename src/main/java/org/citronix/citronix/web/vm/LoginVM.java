package org.citronix.citronix.web.vm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginVM {
    @NotBlank
    @Size(min = 4, max = 200)
    private String username;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
}
