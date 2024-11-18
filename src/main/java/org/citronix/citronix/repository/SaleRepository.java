package org.citronix.citronix.repository;

import org.citronix.citronix.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
