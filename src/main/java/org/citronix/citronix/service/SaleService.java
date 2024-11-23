package org.citronix.citronix.service;

import lombok.Setter;
import org.citronix.citronix.domain.Sale;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SaleService {

    Sale save(Sale sale);

    Sale update(Sale sale);

    Sale findById(Long id);

    void delete(Long id);

    Page<Sale> findAll(int page, int size);
}
