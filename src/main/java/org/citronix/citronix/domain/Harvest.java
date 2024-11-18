package org.citronix.citronix.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.citronix.citronix.domain.eum.Season;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Season season;

    private LocalDateTime harvestedAt;

    @OneToMany(mappedBy = "harvest")
    private List<HarvestDetail> harvestDetails;

    @OneToMany(mappedBy = "harvest")
    private List<Sale> sales;

    @Override
    public String toString() {
        return "Harvest{" +
                "id=" + id +
                ", season=" + season +
                ", harvestedAt=" + harvestedAt +
                ", harvestDetails=" + harvestDetails.size() +
                ", sales=" + sales.size() +
                '}';
    }
}