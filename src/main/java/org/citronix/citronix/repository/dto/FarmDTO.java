package org.citronix.citronix.repository.dto;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.citronix.citronix.domain.Field;

import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FarmDTO {
    private Long id;

    private String name;

    private String location;

    private LocalDateTime createdAt;

    private Double area;


    private Double fieldsArea;
}
