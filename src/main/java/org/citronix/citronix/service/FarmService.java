package org.citronix.citronix.service;

import org.citronix.citronix.domain.Farm;
import org.springframework.stereotype.Service;

@Service
public interface FarmService {

    Farm save(Farm farm);

    Farm update(Farm farm);

    Farm findById(Long id);
}