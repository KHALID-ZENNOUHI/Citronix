package org.citronix.citronix.service.impl;

import org.citronix.citronix.domain.Farm;
import org.citronix.citronix.domain.Field;
import org.citronix.citronix.domain.Tree;
import org.citronix.citronix.repository.TreeRepository;
import org.citronix.citronix.service.FieldService;
import org.citronix.citronix.service.TreeService;
import org.citronix.citronix.web.errors.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
@Component
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;
    private static final int MAX_TREES_PER_1000_M2 = 10;
    private final FieldService fieldService;

    public TreeServiceImpl(TreeRepository treeRepository, FieldService fieldService) {
        this.treeRepository = treeRepository;
        this.fieldService = fieldService;
    }

    @Override
    public Tree save(Tree tree) {
        if (tree.getId() != null) throw new IdMustBeNullException();
        if (tree.getHarvestDetails() != null && !tree.getHarvestDetails().isEmpty()) throw new TreesInFieldMustBeEmptyException();
        if (!isPlantingSeason(tree.getPlantedAt())) throw new PlantingSeasonIsOverException();
        Field field = fieldService.findById(tree.getField().getId());
        Farm farm = field.getFarm();
        if (tree.getPlantedAt().isBefore(farm.getCreatedAt())) throw new PlantingDateBeforeFarmCreationException();
        checkTreeSpacing(field);
        return treeRepository.save(tree);
    }

    @Override
    public Tree update(Tree tree) {
        if (tree.getId() == null) throw new IdMustBeNotNullException();
        if (!isPlantingSeason(tree.getPlantedAt())) throw new PlantingSeasonIsOverException();
        Field field = fieldService.findById(tree.getField().getId());
        checkTreeSpacing(field);
        return treeRepository.save(tree);
    }

    @Override
    public Tree findById(Long id) {
        if (id == null) throw new IdMustBeNotNullException();
        return treeRepository.findById(id).orElseThrow(TreeNotFoundException::new);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<Tree> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return treeRepository.findAll(pageable);
    }

    @Override
    public Boolean isPlantingSeason(LocalDateTime plantingDate) {
        Month month = plantingDate.getMonth();
        return month.equals(Month.MARCH) || month.equals(Month.APRIL) || month.equals(Month.MAY);
    }

    public void checkTreeSpacing(Field field) {
        double maxAllowedTrees = (field.getArea() / 1000) * MAX_TREES_PER_1000_M2;
        if (field.getTrees().size() > maxAllowedTrees) throw new MaxAllowedTreesDepassedException();
    }

}
