package org.citronix.citronix.repository;

import org.citronix.citronix.domain.Harvest;
import org.citronix.citronix.domain.eum.Season;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {
    Page<Harvest> findAll(Pageable pageable);

}
