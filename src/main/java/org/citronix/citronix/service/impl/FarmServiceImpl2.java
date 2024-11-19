package org.citronix.citronix.service.impl;

import org.citronix.citronix.domain.Farm;
import org.citronix.citronix.repository.FarmRepository;
import org.citronix.citronix.service.FarmService;
import org.citronix.citronix.service.FieldService;
import org.citronix.citronix.web.errors.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FarmServiceImpl2 implements FarmService {

    private final FarmRepository farmRepository;
    private final FieldServiceImpl1 fieldService;

    public FarmServiceImpl2(FarmRepository farmRepository, FieldServiceImpl1 fieldService) {
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
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public Farm search(String name, String location, Double area) {
        return null;
    }
}
