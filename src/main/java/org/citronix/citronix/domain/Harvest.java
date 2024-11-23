package org.citronix.citronix.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.citronix.citronix.domain.eum.Season;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Season season;

    @NotNull
    @PastOrPresent
    private LocalDateTime harvestedAt;

    @NotNull
    @PositiveOrZero
    private Double totalQuantity;

    @OneToMany(mappedBy = "harvest")
    private List<HarvestDetail> harvestDetails;

    @OneToMany(mappedBy = "harvest")
    private List<Sale> sales;

    public Long getId() {
        return id;
    }

    public Season getSeason() {
        return season;
    }

    public @NotNull @PastOrPresent LocalDateTime getHarvestedAt() {
        return harvestedAt;
    }

    public @NotNull @PositiveOrZero Double getTotalQuantity() {
        return totalQuantity;
    }

    @JsonIgnore
    public List<HarvestDetail> getHarvestDetails() {
        return harvestDetails;
    }

    @JsonIgnore
    public List<Sale> getSales() {
        return sales;
    }

    @Override
    public String toString() {
        return "Harvest{" +
                "id=" + id +
                ", harvestedAt=" + harvestedAt +
                ", totalQuantity=" + totalQuantity +
                ", harvestDetails=" + harvestDetails.size() +
                ", sales=" + sales.size() +
                '}';
    }
}
