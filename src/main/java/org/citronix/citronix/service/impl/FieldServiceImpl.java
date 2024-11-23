package org.citronix.citronix.service.impl;

import org.citronix.citronix.domain.Farm;
import org.citronix.citronix.domain.Field;
import org.citronix.citronix.repository.FieldRepository;
import org.citronix.citronix.service.FarmService;
import org.citronix.citronix.service.FieldService;
import org.citronix.citronix.service.TreeService;
import org.citronix.citronix.web.errors.FieldNotFoundException;
import org.citronix.citronix.web.errors.IdMustBeNotNullException;
import org.citronix.citronix.web.errors.IdMustBeNullException;
import org.citronix.citronix.web.errors.TreesInFieldMustBeEmptyException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;
    private final FarmService farmService;
    private final TreeService treeService;

    public FieldServiceImpl(FieldRepository fieldRepository,@Lazy FarmService farmService,@Lazy TreeService treeService) {
        this.fieldRepository = fieldRepository;
        this.farmService = farmService;
        this.treeService = treeService;
    }

    @Override
    public Field save(Field field) {
        if (field.getId() != null) throw new IdMustBeNullException();
        if (field.getTrees() != null && !field.getTrees().isEmpty()) throw new TreesInFieldMustBeEmptyException();
        Farm farm = farmService.findById(field.getFarm().getId());
        field.setFarm(farm);
        checkFieldAreaLessThanFiftyPercentOfFarmArea(field);
        farmService.checkFarmAreaGreaterThanSumOfFieldsArea(farm);
        farmService.checkMaxFieldOfFarmIsTen(farm);
        return fieldRepository.save(field);
    }

    @Override
    public Field update(Field field) {
        findById(field.getId());
        return fieldRepository.save(field);
    }

    @Override
    public Field findById(Long id) {
        if (id == null) throw new IdMustBeNotNullException();
        return fieldRepository.findById(id).orElseThrow(FieldNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        if (id == null) throw new IdMustBeNotNullException();
        Field field = findById(id);
        if (field.getTrees() != null && !field.getTrees().isEmpty()){
            field.getTrees().forEach(tree -> treeService.delete(tree.getId()));
        }
        fieldRepository.deleteById(field.getId());
    }

    @Override
    public void deleteAllByFarmId(Long farmId) {
        if (farmId == null) throw new IdMustBeNotNullException();
        fieldRepository.deleteAllByFarmId(farmId);
    }

    @Override
    public Page<Field> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("area").ascending());
        return fieldRepository.findAll(pageable);
    }

    public void checkFieldAreaLessThanFiftyPercentOfFarmArea(Field field) {
        if (field.getArea() > field.getFarm().getArea() * 0.5) {
            throw new IllegalArgumentException("Field area must be less than 50% of farm area");
        }
    }


}
