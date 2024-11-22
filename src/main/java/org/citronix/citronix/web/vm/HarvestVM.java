package org.citronix.citronix.web.vm;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
public class HarvestVM {
    @PastOrPresent
    LocalDateTime harvestDate;

    @NotNull
    Long fieldId;
}
