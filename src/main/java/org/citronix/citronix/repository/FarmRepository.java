package org.citronix.citronix.repository;

import org.citronix.citronix.domain.Farm;
import org.citronix.citronix.repository.dto.FarmDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    @Query("SELECT new org.citronix.citronix.repository.dto.FarmDTO(" +
            "f.id, f.name, f.location, f.createdAt, f.area, SUM(fl.area)) " +
            "FROM Farm f JOIN f.fields fl WHERE SUM(fl.area) < :area")
    List<FarmDTO> findFarmWithAreaLessThan(Double area);

    List<Farm> findAll();
}
