package org.citronix.citronix.web.vm;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.citronix.citronix.domain.Farm;
import org.citronix.citronix.domain.Tree;

import java.util.List;

@Setter
@Getter
public class FieldVM {
    private Long id;

    @DecimalMin(value = "1000", message = "Field area must be at least 0.1 hectares.")
    private Double area;

    @NotNull
    private Long FarmId;


    private List<Tree> trees;
}
