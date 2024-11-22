package org.citronix.citronix.service.impl;

import jakarta.transaction.Transactional;
import org.citronix.citronix.domain.Field;
import org.citronix.citronix.domain.Harvest;
import org.citronix.citronix.domain.HarvestDetail;
import org.citronix.citronix.domain.Tree;
import org.citronix.citronix.repository.HarvestDetailRepository;
import org.citronix.citronix.repository.HarvestRepository;
import org.citronix.citronix.service.FieldService;
import org.citronix.citronix.service.HarvestService;
import org.citronix.citronix.util.Util;
import org.citronix.citronix.web.errors.HarvestAlreadyExistsForThisSeasonException;
import org.citronix.citronix.web.errors.HarvestNotFoundException;
import org.citronix.citronix.web.errors.IdMustBeNotNullException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final HarvestDetailRepository harvestDetailRepository;
    private final FieldService fieldService;

    public HarvestServiceImpl(HarvestRepository harvestRepository, HarvestDetailRepository harvestDetailRepository, FieldService fieldService) {
        this.harvestRepository = harvestRepository;
        this.harvestDetailRepository = harvestDetailRepository;
        this.fieldService = fieldService;
    }


    @Transactional
    @Override
    public Harvest save(LocalDateTime harvestDate, Long fieldId) {
        isHarvestExistsBySeasonAndYear(fieldId, harvestDate);
        Field field = fieldService.findById(fieldId);
        Harvest harvest = new Harvest();
        Double totalQuantity = 0.0;
        harvest.setTotalQuantity(totalQuantity);
        harvest.setHarvestedAt(harvestDate);
        harvest.setSeason(Util.getSeason(harvestDate));
        Harvest savedHarvest = harvestRepository.save(harvest);
        List< HarvestDetail> harvestDetails = new ArrayList<>();
        for (Tree tree : field.getTrees()) {
            HarvestDetail harvestDetail = new HarvestDetail();
            harvestDetail.setTree(tree);
            harvestDetail.setHarvest(savedHarvest);
            harvestDetail.setQuantity(tree.calculateProductivity());
            harvestDetails.add(harvestDetail);
            totalQuantity += harvestDetail.getQuantity();
        }
        harvestDetailRepository.saveAll(harvestDetails);
        savedHarvest.setTotalQuantity(totalQuantity);
        savedHarvest.setHarvestDetails(harvestDetails);
        return harvestRepository.save(savedHarvest);
    }

    @Override
    public Harvest update(LocalDateTime harvestDate, Long fieldId) {
        isHarvestExistsBySeasonAndYear(fieldId, harvestDate);
        Field field = fieldService.findById(fieldId);
        Harvest harvest = new Harvest();
        Double totalQuantity = 0.0;
        harvest.setTotalQuantity(totalQuantity);
        harvest.setHarvestedAt(harvestDate);
        harvest.setSeason(Util.getSeason(harvestDate));
        Harvest savedHarvest = harvestRepository.save(harvest);
        List< HarvestDetail> harvestDetails = new ArrayList<>();
        for (Tree tree : field.getTrees()) {
            HarvestDetail harvestDetail = new HarvestDetail();
            harvestDetail.setTree(tree);
            harvestDetail.setHarvest(savedHarvest);
            harvestDetail.setQuantity(tree.calculateProductivity());
            harvestDetails.add(harvestDetail);
            totalQuantity += harvestDetail.getQuantity();
        }
        harvestDetailRepository.saveAll(harvestDetails);
        savedHarvest.setTotalQuantity(totalQuantity);
        savedHarvest.setHarvestDetails(harvestDetails);
        return harvestRepository.save(savedHarvest);
    }

    @Override
    public Harvest findById(Long id) {
        if (id == null) throw new IdMustBeNotNullException();
        return harvestRepository.findById(id).orElseThrow(HarvestNotFoundException::new);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<Harvest> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("harvestedAt").descending());
        return harvestRepository.findAll(pageable);
    }

    public void isHarvestExistsBySeasonAndYear(Long fieldId, LocalDateTime harvestDate) {
        List<Harvest> harvests = getHarvestsByFieldId(fieldId);
        harvests.forEach(h -> {
            if (h.getSeason().equals(Util.getSeason(harvestDate)) && h.getHarvestedAt().getYear() == harvestDate.getYear()) {
                throw new HarvestAlreadyExistsForThisSeasonException();
            }
        });
    }

    public List<Harvest> getHarvestsByFieldId(Long fieldId) {
        Field field = fieldService.findById(fieldId);
        Tree tree = field.getTrees().get(0);
        return tree.getHarvestDetails().stream().map(HarvestDetail::getHarvest).toList();
    }



}
