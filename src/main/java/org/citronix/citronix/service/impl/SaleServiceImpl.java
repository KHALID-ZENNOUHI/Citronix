package org.citronix.citronix.service.impl;

import org.citronix.citronix.domain.Harvest;
import org.citronix.citronix.domain.Sale;
import org.citronix.citronix.repository.SaleRepository;
import org.citronix.citronix.service.HarvestService;
import org.citronix.citronix.service.SaleService;
import org.citronix.citronix.web.errors.IdMustBeNotNullException;
import org.citronix.citronix.web.errors.IdMustBeNullException;
import org.citronix.citronix.web.errors.SaleNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final HarvestService harvestService;

    public SaleServiceImpl(SaleRepository saleRepository, HarvestService harvestService) {
        this.saleRepository = saleRepository;
        this.harvestService = harvestService;
    }


    @Override
    public Sale save(Sale sale) {
        if (sale.getId() != null) throw new IdMustBeNullException();
        Harvest harvest = harvestService.findById(sale.getHarvest().getId());
        Sale sale1 = saleRepository.save(sale);
        sale1.setHarvest(harvest);
        return sale1;
    }

    @Override
    public Sale update(Sale sale) {
        if (sale.getId() == null) throw new IdMustBeNotNullException();
        Harvest harvest = harvestService.findById(sale.getHarvest().getId());
        Sale sale1 = saleRepository.save(sale);
        sale1.setHarvest(harvest);
        return sale1;
    }

    @Override
    public Sale findById(Long id) {
        if (id == null) throw new IdMustBeNotNullException();
        Sale sale = saleRepository.findById(id).orElseThrow(SaleNotFoundException::new);
        Harvest harvest = harvestService.findById(sale.getHarvest().getId());
        sale.setHarvest(harvest);
        return sale;
    }

    @Override
    public void delete(Long id) {
        if (id == null) throw new IdMustBeNotNullException();
        Sale sale = saleRepository.findById(id).orElseThrow(SaleNotFoundException::new);
        saleRepository.delete(sale);
    }

    @Override
    public Page<Sale> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
        Page<Sale> sales = saleRepository.findAll(pageable);
        sales.stream().forEach(sale -> {
            Harvest harvest = harvestService.findById(sale.getHarvest().getId());
            sale.setHarvest(harvest);
        });
        return sales;
    }
}
