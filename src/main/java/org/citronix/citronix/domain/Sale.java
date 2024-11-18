package org.citronix.citronix.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be greater than 0.")
    private Double unitPrice;

    @NotBlank
    private String client;

    @NotNull
    @PastOrPresent
    private LocalDateTime date;

    @ManyToOne
    private Harvest harvest;

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", unitPrice=" + unitPrice +
                ", client='" + client + '\'' +
                ", date=" + date +
                ", harvest=" + harvest +
                '}';
    }
}
