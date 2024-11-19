package org.citronix.citronix.service;

import org.citronix.citronix.domain.Farm;
import org.citronix.citronix.repository.dto.FarmDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FarmService {

    Farm save(Farm farm);

    Farm update(Farm farm);

    Farm findById(Long id);

    void delete(Long id);

    Page<Farm> findAll(int page, int size);

    Farm search(String name, String location, Double area);

    void checkFarmAreaGreaterThanSumOfFieldsArea(Farm farm);

    List<FarmDTO> getFarmWithFieldsAreaLessThan(Double area);
}
