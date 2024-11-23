package org.citronix.citronix.web.vm;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.citronix.citronix.domain.Field;

import java.time.LocalDateTime;

@Setter
@Getter
public class TreeVM {
    private Long id;

    @NotNull
    @PastOrPresent
    private LocalDateTime plantedAt;

    @NotNull
    private Long fieldId;
}
