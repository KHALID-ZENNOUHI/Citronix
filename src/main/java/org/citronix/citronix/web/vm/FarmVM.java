package org.citronix.citronix.web.vm;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @DecimalMin(value = "2000", message = "Farm area must be at least 2000 m^2.")
    private Double area;

    List<FieldVM> fields;
}
