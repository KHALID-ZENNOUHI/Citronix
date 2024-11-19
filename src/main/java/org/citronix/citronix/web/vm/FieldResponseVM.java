package org.citronix.citronix.web.vm;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.citronix.citronix.domain.Tree;

import java.util.List;

@Getter
@Setter
public class FieldResponseVM {
    private Long id;

    private Double area;
}
