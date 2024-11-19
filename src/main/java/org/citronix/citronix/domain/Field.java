package org.citronix.citronix.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin(value = "1000", message = "Field area must be at least 0.1 hectares.")
    private Double area;

    @ManyToOne
    private Farm farm;

    @OneToMany(mappedBy = "field")
    private List<Tree> trees;

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", area=" + area +
                ", farm=" + farm +
                ", trees=" + trees.size() +
                '}';
    }
}
