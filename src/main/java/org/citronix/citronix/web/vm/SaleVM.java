package org.citronix.citronix.web.vm;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.citronix.citronix.domain.Harvest;

import java.time.LocalDateTime;

@Setter
@Getter
public class SaleVM {

    private Long id;

    @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be greater than 0.")
    private Double unitPrice;

    @NotBlank
    private String client;

    @NotNull
    @PastOrPresent
    private LocalDateTime date;

    private Long harvestId;

    private Double income;
}
