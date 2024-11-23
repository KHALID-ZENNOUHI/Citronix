package org.citronix.citronix.repository;

import org.citronix.citronix.domain.HarvestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, Long> {
}
