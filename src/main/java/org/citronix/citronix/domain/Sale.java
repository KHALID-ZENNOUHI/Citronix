package org.citronix.citronix.domain;

import jakarta.persistence.*;
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

    private Double unitPrice;

    private String client;

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
