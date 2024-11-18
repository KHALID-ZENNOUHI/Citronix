package org.citronix.citronix.service.impl;

import org.citronix.citronix.domain.Farm;
import org.citronix.citronix.repository.FarmRepository;
import org.citronix.citronix.service.FarmService;
import org.citronix.citronix.web.errors.FarmNotFoundException;
import org.citronix.citronix.web.errors.IdMustBeNotNullException;
import org.citronix.citronix.web.errors.IdMustBeNullException;
import org.springframework.stereotype.Component;

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

}
