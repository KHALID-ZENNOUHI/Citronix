package org.citronix.citronix.service;

import org.citronix.citronix.domain.Field;
import org.citronix.citronix.domain.Harvest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface HarvestService {
    Harvest save(LocalDateTime harvestDate, Long fieldId);

    Harvest update(Long id,LocalDateTime harvestDate, Long fieldId);

    Harvest findById(Long id);

    void delete(Long id);

    Page<Harvest> findAll(int page, int size);

    List<Harvest> getHarvestsByFieldId(Long fieldId);

    Field getFieldByHarvestId(Long harvestId);
}
