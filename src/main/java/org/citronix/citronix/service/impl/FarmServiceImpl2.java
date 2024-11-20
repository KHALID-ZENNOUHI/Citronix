package org.citronix.citronix.service.impl;

import org.citronix.citronix.domain.Farm;
import org.citronix.citronix.repository.FarmRepository;
import org.citronix.citronix.repository.dto.FarmDTO;
import org.citronix.citronix.service.FarmService;
import org.citronix.citronix.service.FieldService;
import org.citronix.citronix.web.errors.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FarmServiceImpl2 implements FarmService {

    private final FarmRepository farmRepository;
    private final FieldService fieldService;

    public FarmServiceImpl2(FarmRepository farmRepository,  FieldService fieldService) {
        this.farmRepository = farmRepository;
        this.fieldService = fieldService;
    }

    @Override
    public Farm save(Farm farm) {
        if (farm.getId() != null) throw new IdMustBeNullException();
        if (farm.getFields() == null || farm.getFields().isEmpty()) throw new FieldsInFarmMustNotBeEmptyException();
        Farm farm1 = farmRepository.save(farm);
        farm.getFields().forEach(field -> {
            field.setFarm(farm1);
            fieldService.save(field);
        });
        return farm1;
    }

    @Override
    public Farm update(Farm farm) {
        findById(farm.getId());
        return farmRepository.save(farm);
    }

    @Override
    public Farm findById(Long id) {
        if (id == null) throw new IdMustBeNotNullException();
        return farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<Farm> findAll(int page, int size) {
        return null;
    }

    @Override
    public Farm search(String name, String location, Double area) {
        return null;
    }

    @Override
    public void checkFarmAreaGreaterThanSumOfFieldsArea(Farm farm) {

    }

    @Override
    public List<FarmDTO> getFarmWithFieldsAreaLessThan(Double area) {
        return List.of();
    }

    @Override
    public void checkMaxFieldOfFarmIsTen(Farm farm) {
        if (farm.getFields().size() > 10) {
            throw new IllegalArgumentException("Farm can have maximum 10 fields");
        }
    }
}
