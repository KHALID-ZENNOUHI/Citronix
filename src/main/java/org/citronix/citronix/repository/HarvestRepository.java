package org.citronix.citronix.repository;

import org.citronix.citronix.domain.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {
}
