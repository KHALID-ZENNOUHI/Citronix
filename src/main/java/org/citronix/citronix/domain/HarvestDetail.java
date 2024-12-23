package org.citronix.citronix.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HarvestDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin(value = "0.0", inclusive = false, message = "Harvest quantity must be greater than 0.")
    private Double quantity;

    @ManyToOne
    private Tree tree;

    @ManyToOne
    private Harvest harvest;

    @Override
    public String toString() {
        return "HarvestDetail{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", tree=" + tree +
                ", harvest=" + harvest +
                '}';
    }
}
