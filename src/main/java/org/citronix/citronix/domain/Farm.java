package org.citronix.citronix.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @DecimalMin(value = "2000", message = "Farm area must be at least 2000 m^2.")
    private Double area;

    @OneToMany(mappedBy = "farm")
    private List<Field> fields;

    @Override
    public String toString() {
        return "Farm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", fields=" + fields.size() +
                '}';
    }
}
