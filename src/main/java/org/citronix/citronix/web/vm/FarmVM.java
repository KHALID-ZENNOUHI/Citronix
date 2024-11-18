package org.citronix.citronix.web.vm;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FarmVM {
    private Long id;

    @NotBlank
    @Size(min = 5, max = 150)
    private String name;

    @NotBlank
    @Size(min = 5, max = 150)
    private String location;

    @NotNull
    @PastOrPresent
    private LocalDateTime createdAt;

    @NotNull
    @Positive
    private Double area;
}
