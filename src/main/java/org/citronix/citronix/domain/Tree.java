package org.citronix.citronix.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PastOrPresent
    private LocalDateTime plantedAt;

    @ManyToOne
    private Field field;

    @OneToMany(mappedBy = "tree")
    private List<HarvestDetail> harvestDetails;

    private static final int SEASONS_PER_YEAR = 4;

    public Period calculateAge() {
        return Period.between(plantedAt.toLocalDate(), LocalDateTime.now().toLocalDate());
    }

    public double calculateProductivity() {
        Period age = calculateAge();
        int years = age.getYears();

        if (years < 3) {
            return 2.5 * SEASONS_PER_YEAR;
        } else if (years <= 10) {
            return 12 * SEASONS_PER_YEAR;
        } else if (years <= 20) {
            return 20 * SEASONS_PER_YEAR;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Tree{" +
                "id=" + id +
                ", plantedAt=" + plantedAt +
                ", field=" + field +
                ", harvestDetails=" + harvestDetails.size() +
                '}';
    }
}
