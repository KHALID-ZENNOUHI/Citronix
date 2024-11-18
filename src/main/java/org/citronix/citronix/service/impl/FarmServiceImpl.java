package org.citronix.citronix.service.impl;

import org.citronix.citronix.domain.Farm;
import org.citronix.citronix.repository.FarmRepository;
import org.citronix.citronix.service.FarmService;
import org.citronix.citronix.web.errors.FarmNotFoundException;
import org.citronix.citronix.web.errors.IdMustBeNotNullException;
import org.citronix.citronix.web.errors.IdMustBeNullException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class FarmServiceImpl implements FarmService {
    private final FarmRepository farmRepository;

    public FarmServiceImpl(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public Farm save(Farm farm) {
        if (farm.getId() != null) throw new IdMustBeNullException();
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
    public Farm search(String name, String location, Double area) {

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


        return farmRepository.findOne(example)
                .orElseThrow(FarmNotFoundException::new);
    }

}
