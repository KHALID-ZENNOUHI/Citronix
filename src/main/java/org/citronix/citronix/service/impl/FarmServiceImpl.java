package org.citronix.citronix.service.impl;

import org.citronix.citronix.domain.Farm;
import org.citronix.citronix.domain.Field;
import org.citronix.citronix.repository.FarmRepository;
import org.citronix.citronix.repository.dto.FarmDTO;
import org.citronix.citronix.service.FarmService;
import org.citronix.citronix.service.FieldService;
import org.citronix.citronix.web.errors.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("FarmServiceImpl")
public class FarmServiceImpl implements FarmService {
    private final FarmRepository farmRepository;
    private final FieldService fieldService;

    public FarmServiceImpl(FarmRepository farmRepository, FieldService fieldService) {
        this.farmRepository = farmRepository;
        this.fieldService = fieldService;
    }

    @Override
    public Farm save(Farm farm) {
        if (farm.getId() != null) throw new IdMustBeNullException();
        if (farm.getFields()!= null  && !farm.getFields().isEmpty()) throw new FieldsInFarmMustBeEmptyException();
        return farmRepository.save(farm);
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
        if (id == null) throw new IdMustBeNotNullException();
        Farm farm = findById(id);
        if (farm.getFields() != null && !farm.getFields().isEmpty()) {
            farm.getFields().forEach(field -> {
                fieldService.delete(field.getId());
            });
        }
        farmRepository.delete(farm);
    }

    @Override
    public List<Farm> search(String name, String location, Double area) {

        if (name == null && location == null && area == null) {
            throw new IllegalArgumentException("At least one search criteria must be provided.");
        }

        Farm exampleFarm = Farm.builder()
                .name(name)
                .location(location)
                .area(area)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnoreNullValues()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("location", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("area", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("createdAt", ExampleMatcher.GenericPropertyMatchers.exact());


        Example<Farm> example = Example.of(exampleFarm, matcher);


        return farmRepository.findAll(example);
    }

    @Override
    public List<FarmDTO> getFarmWithFieldsAreaLessThan(Double area) {
        return farmRepository.findFarmWithAreaLessThan(area);
    }

    @Override
    public void checkFarmAreaGreaterThanSumOfFieldsArea(Farm farm) {
        if (farm.getArea() < farm.getFields().stream().mapToDouble(Field::getArea).sum()) {
            throw new IllegalArgumentException("Farm area must be greater than sum of fields area");
        }
    }

    @Override
    public Page<Farm> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
        return farmRepository.findAll(pageable);
    }

    @Override
    public void checkMaxFieldOfFarmIsTen(Farm farm) {
        if (farm.getFields().size() > 10) {
            throw new IllegalArgumentException("Farm can have at most 10 fields");
        }
    }



}
